package com.ep.yc.yctcpgatewayserver.netty.cache;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;

/**
 * @Author WQY
 * @Date 2019/11/11 15:05
 * @Version 1.0
 */
public class CachePacket {

    public static String cacheStr = "";

    public static HashMap<String, Channel> strCtxMap = new HashMap<String, Channel>();
    public static HashMap<Channel, String> ctxStrMap = new HashMap<Channel, String>();

}
