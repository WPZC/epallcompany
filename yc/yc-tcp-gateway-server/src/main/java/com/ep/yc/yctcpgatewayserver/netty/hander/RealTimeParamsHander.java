package com.ep.yc.yctcpgatewayserver.netty.hander;

import com.ep.yc.yctcpgatewayserver.netty.packetentity.decoder.Decoder0001;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.decoder.Decoder0003;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.ecoder.ResEcoder0003;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author WQY
 * @Date 2019/11/11 17:16
 * @Version 1.0
 */
public class RealTimeParamsHander extends SimpleChannelInboundHandler<Decoder0003> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Decoder0003 decoder0003) throws Exception {

        System.out.println("接收到的实时参数为:"+decoder0003.getOutMsg());

        ResEcoder0003 resEcoder0003 = new ResEcoder0003();

        resEcoder0003.setOutMsg(decoder0003.getOutMsg());

        ctx.channel().writeAndFlush(resEcoder0003);
    }
}
