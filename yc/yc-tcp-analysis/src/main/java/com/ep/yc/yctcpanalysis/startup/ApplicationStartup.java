package com.ep.yc.yctcpanalysis.startup;


import com.ep.yc.yctcpanalysis.cache.task.DevicePlatformTask;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;



/**
 * springboot启动的启动的方法
 * 启动事件写在onApplicationEvent
 */
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent  event) {

        System.out.println("初始化参数");
        DevicePlatformTask task = new DevicePlatformTask();
        task.cacheDevicePlatformData();
    }
}
