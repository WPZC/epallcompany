package com.ep.yc.ycview.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @Author WQY
 * @Date 2019/10/9 10:52
 * @Version 1.0
 */
@Configuration
public class MultipartConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小 10M
        factory.setMaxFileSize("10240KB");
        /// 总上传数据大小 10M
        factory.setMaxRequestSize("10240KB");
        return factory.createMultipartConfig();
    }
}


