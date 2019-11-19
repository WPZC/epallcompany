package com.ep.yc.yctcpanalysis.cache.thread;


import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author WQY
 * @date 2019/9/7 10:11
 */
public class ThreadPoolContext {

    /**
     * 定时线程池
     */
    public static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);


}
