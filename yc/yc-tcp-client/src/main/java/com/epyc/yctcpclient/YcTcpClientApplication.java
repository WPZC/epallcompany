package com.epyc.yctcpclient;

import com.epyc.yctcpclient.startup.ApplicationStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class YcTcpClientApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(YcTcpClientApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
    }
}
