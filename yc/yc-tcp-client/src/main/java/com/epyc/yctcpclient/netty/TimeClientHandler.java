package com.epyc.yctcpclient.netty;

import com.epyc.yctcpclient.context.BeanUtils;
import com.epyc.yctcpclient.context.Variable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/5/17.
 * 用于对网络事件进行读写操作
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

    private String ip;
    private Integer port;

    public TimeClientHandler(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    /**
     * 当客户端和服务端 TCP 链路建立成功之后，Netty 的 NIO 线程会调用 channelActive 方法
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {


        Variable.map.put(ip + ":" + port, ctx);
        System.out.println("客户端连接成功");
        //修改对接平台状态
        BeanUtils.sendDbBase.updatePlatformState(0L,ip,port+"");
        //}
        /*if (Util.map.get(key).equals(ctx)){

        }else {
            Util.map.put()
        }*/
//        String reqMsg = "1234";
//        byte[] reqMsgByte = reqMsg.getBytes("UTF-8");
//        ByteBuf reqByteBuf = Unpooled.buffer(reqMsgByte.length);
//        /**
//         * writeBytes：将指定的源数组的数据传输到缓冲区
//         * 调用 ChannelHandlerContext 的 writeAndFlush 方法将消息发送给服务器
//         */
//        System.out.println(ctx.channel().id());
//        System.out.println(ctx.channel().id().asLongText());
//        reqByteBuf.writeBytes(reqMsgByte);
        //ctx.writeAndFlush(reqByteBuf);
    }

    /**
     * 当服务端返回应答消息时，channelRead 方法被调用，从 Netty 的 ByteBuf 中读取并打印应答消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println(Thread.currentThread().getName() + ",Server return Message：" + body);
        ctx.close();
    }

    /**
     * 当发生异常时，打印异常 日志，释放客户端资源
     * 断开连接
     *
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /**释放资源*/
        for (String key:Variable.map.keySet()){
            if(Variable.map.get(key)==ctx){
                logger.warning("客户端断开连接 : " + cause.getMessage());
                Variable.map.remove(key);
                //修改对接平台状态
                BeanUtils.sendDbBase.updatePlatformState(1L,port+"",ip);
                ctx.close();
            }
        }

    }

    /**
     * 客户端与服务端 断连时 执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception, IOException
    {
        /**释放资源*/
        for (String key:Variable.map.keySet()){
            if(Variable.map.get(key)==ctx){
                System.out.println("客户端与服务端断开连接");
                Variable.map.remove(key);
                //修改对接平台状态
                BeanUtils.sendDbBase.updatePlatformState(1L,ip,port+"");
                ctx.close();
            }
        }
    }
}