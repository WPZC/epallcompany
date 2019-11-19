package com.ep.yc.yctcpanalysis;

import com.ep.yc.yctcpanalysis.startup.ApplicationStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class YcTcpAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(YcTcpAnalysisApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
    }

}
