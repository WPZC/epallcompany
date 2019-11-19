package com.ep.yc.yctcpgatewayserver;

import com.ep.yc.yctcpgatewayserver.startup.ApplicationStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class YcTcpGatewayServerApplication {

    public static void main(String[] args) {
        //启动
        SpringApplication springApplication = new SpringApplication(YcTcpGatewayServerApplication.class);
        //添加启动监听
        springApplication.addListeners(new ApplicationStartup());

        springApplication.run(args);
    }

}
