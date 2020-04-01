package com.sheng.farm.mall.core;

import com.google.common.collect.Maps;
import com.sheng.farm.mall.core.properties.SymphonyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author takesi
 * @date 2020-04-01
 */
@Configuration
@EnableConfigurationProperties({SymphonyProperties.class})
public class FarmMallWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private SymphonyProperties symphonyProperties;

    @Resource
    public ThymeleafViewResolver thymeleafViewResolver(ThymeleafViewResolver resolver) {
        Map<String, String> variables = Maps.newConcurrentMap();
        variables.put("static", "http://127.0.0.1:8080/static");
        variables.put("upload", "http://127.0.0.1:8080/upload");
        resolver.setStaticVariables(variables);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/category").setViewName("goods/category");
        registry.addViewController("/cart").setViewName("order/cart");
        registry.addViewController("/homestay").setViewName("home/homestay");
        registry.addViewController("/mine").setViewName("user/mine");
        registry.addViewController("/login").setViewName("user/login");
        registry.addViewController("/signup").setViewName("user/signup");
    }
}
