package com.ep.yc.yctcpgatewayserver.startup;

import com.ep.yc.yctcpgatewayserver.netty.TimeServer;
import com.ep.yc.yctcpgatewayserver.utils.thread.ThreadPoolUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.util.concurrent.ThreadPoolExecutor;


/**
 * springboot启动的启动的方法
 * 启动事件写在onApplicationEvent
 * @author WQY
 */
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent  event) {
        System.out.println("成功启动------+++++++++++++");
        try {
            //启动netty
            ThreadPoolExecutor nettyPool = new ThreadPoolUtils().getNewInstance(1,1,
                    "nettyPool",1);
            //启动netty的server
            nettyPool.execute(()->{
                try {
                    System.out.println("开始启动");
                    new TimeServer().bind(8200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
