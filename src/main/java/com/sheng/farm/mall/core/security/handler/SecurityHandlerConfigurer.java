package com.sheng.farm.mall.core.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sheng.farm.mall.core.wrapper.Wrapper;
import com.sheng.farm.mall.core.wrapper.WrapMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author takesi
 * @date 2020-04-01
 */
@Configuration
public class SecurityHandlerConfigurer implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(SecurityHandlerConfigurer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return ((request, response, authentication) -> {
            String redirectUri = request.getParameter("redirect_uri");
            final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

            if (StringUtils.isNotEmpty(redirectUri)) {
                //重定向指定的地址
                redirectStrategy.sendRedirect(request, response, redirectUri);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setCharacterEncoding("UTF-8");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                String jsonStr = objectMapper.writeValueAsString(WrapMapper.ok("登出成功"));
                writer.write(jsonStr);
                writer.flush();
            }
        });
    }

    /**
     * 登陆失败，返回401
     *
     * @return AuthenticationFailureHandler
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, e) -> {
            logger.error(e.getMessage(), e);

            String message;
            if (e instanceof BadCredentialsException) {
                message = "密码错误";
            } else {
                message = e.getMessage();
            }
            HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
            response.setStatus(unauthorized.value());
            Wrapper<String> wrapper = WrapMapper.wrap(unauthorized.value(), message);
            response.getWriter().write(objectMapper.writeValueAsString(wrapper));
            // 判断请求接口是否是json请求
            /*if (request.getHeader("x-requested-with") != null && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {

            } else {
                response.sendRedirect("/401");
            }*/
        };
    }

    /**
     * 登陆成功
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("SecurityHandlerConfigurer is running.....");
    }
}
