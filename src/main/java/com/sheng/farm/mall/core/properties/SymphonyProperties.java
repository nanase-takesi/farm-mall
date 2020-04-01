package com.sheng.farm.mall.core.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author takesi
 * @date 2020-04-01
 */
@Getter
@ConfigurationProperties(value = "spring.properties.symphony")
public class SymphonyProperties implements Serializable {

    private static final long serialVersionUID = -6825382658668260708L;

    private String staticPath;

    private String uploadPath;

    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
