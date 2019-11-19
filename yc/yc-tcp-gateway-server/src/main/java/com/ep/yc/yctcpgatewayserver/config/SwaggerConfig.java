package com.ep.yc.yctcpgatewayserver.config;

/**
 * @Author WQY
 * @Date 2019/10/19 10:29
 * @Version 1.0
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的配置类
 * Created by 30721 on 2019/7/7.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean("Authority")
    public Docket apiAuthority() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("yc-tcp-gateway-server-company")
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.ep.yc.yctcpgatewayserver.exposure"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger接口文档")
                .description("")
                //服务条款网
                .termsOfServiceUrl("")
                //版本号
                .version("1.0")
                .build();
    }
}
