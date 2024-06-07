package com.azqore.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "webservice")
@Getter
@Setter
public class WebServiceConfig {

    private String commentsUrl;
}
