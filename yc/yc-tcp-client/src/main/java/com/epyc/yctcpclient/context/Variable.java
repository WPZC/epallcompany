package com.epyc.yctcpclient.context;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/18 14:31
 */
public class Variable {

    public static ConcurrentHashMap<String, ChannelHandlerContext> map = new ConcurrentHashMap<String, ChannelHandlerContext>();
}
