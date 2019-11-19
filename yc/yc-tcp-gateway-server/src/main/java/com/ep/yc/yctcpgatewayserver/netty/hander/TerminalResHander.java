package com.ep.yc.yctcpgatewayserver.netty.hander;

import com.ep.yc.yctcpgatewayserver.netty.packetentity.decoder.Decoder0001;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.ecoder.ResEcoder0001;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author WQY
 * @Date 2019/11/11 17:16
 * @Version 1.0
 */
public class TerminalResHander  extends SimpleChannelInboundHandler<Decoder0001> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Decoder0001 decoder0001) throws Exception {
        System.out.println("接收到的终端应答为:"+decoder0001.getYdJg());
    }
}
