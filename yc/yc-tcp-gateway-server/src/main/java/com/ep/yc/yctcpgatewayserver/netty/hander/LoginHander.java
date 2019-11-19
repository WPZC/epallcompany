package com.ep.yc.yctcpgatewayserver.netty.hander;

import com.ep.yc.yctcpgatewayserver.netty.cache.CachePacket;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.decoder.Decoder0002;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.ecoder.ResEcoder0001;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @Author WQY
 * @Date 2019/11/11 14:41
 * @Version 1.0
 */
public class LoginHander extends SimpleChannelInboundHandler<Decoder0002> {

    private static final Logger logger = Logger.getLogger(LoginHander.class.getName());

    private SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Decoder0002 decoder0002) throws Exception {
        System.out.println("接收到的登录数据为:"+decoder0002.getAuthenticationCode());
        ResEcoder0001 resEcoder0001 = new ResEcoder0001();
        resEcoder0001.setSbid(decoder0002.getSbid());
        resEcoder0001.setYdId(decoder0002.getXxid());
        if(decoder0002.getAuthenticationCode().equals("")){
            //登录失败
            resEcoder0001.setYdRs("01");
        }else{
            resEcoder0001.setTime(format.format(new Date()));
            resEcoder0001.setYdRs("00");
            //加入缓存
            CachePacket.strCtxMap.put(resEcoder0001.getSbid(),ctx.channel());
            CachePacket.ctxStrMap.put(ctx.channel(),resEcoder0001.getSbid());
            logger.info("设备上线："+resEcoder0001.getSbid());
        }
        ctx.channel().writeAndFlush(resEcoder0001);
    }
}
