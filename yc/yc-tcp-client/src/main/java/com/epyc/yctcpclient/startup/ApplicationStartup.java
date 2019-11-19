package com.epyc.yctcpclient.startup;


import com.epyc.yctcpclient.context.BeanUtils;
import com.epyc.yctcpclient.entity.YcPlatformInfoEntity;
import com.epyc.yctcpclient.netty.TimeClient;
import com.epyc.yctcpclient.utils.thread.ThreadPoolUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * springboot启动的启动的方法
 * 启动事件写在onApplicationEvent
 */
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent  event) {
        System.out.println("成功启动------+++++++++++++");
        //System.out.println(BeanUtils.sendDbBase.findAll());

        try {
            List<YcPlatformInfoEntity> list = BeanUtils.sendDbBase.findAll();
            //启动netty
            ThreadPoolExecutor nettyPool = new ThreadPoolUtils().getNewInstance(list.size(),list.size()+5,
                    "nettyPool",list.size()+5);

            for (int i = 0;i<list.size();i++) {
                //启动netty的server
                int finalI = i;
                nettyPool.execute(() -> {
                    try {
                        System.out.println("开始启动");
                        System.out.println(list.get(finalI).getToIp());
                        new TimeClient().connect(list.get(finalI).getToIp(), Integer.parseInt(list.get(finalI).getToPort()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
