package com.ep.yc.yctcpgatewayserver.netty.codec;

import com.ep.yc.yctcpgatewayserver.netty.cache.CachePacket;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.decoder.Decoder0001;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.decoder.Decoder0002;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.decoder.Decoder0003;
import com.ep.yc.yctcpgatewayserver.utils.HexByte;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.logging.Logger;

/**
 * 接受由粘包解码器处理完的数据
 * @Author WQY
 * @Date 2019/11/11 13:53
 * @Version 1.0
 */
public class HexDecoder extends ChannelInboundHandlerAdapter {

    private final int PREFIX = 2;

    private static final Logger logger = Logger.getLogger(HexDecoder.class.getName());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String msgs = (String) msg;
        System.out.println(msg);
        //对消息ID进行判断
        //0001为终端通用挺大
        switch (msgs.substring(PREFIX,PREFIX+4)){
            //终端通用应答
            case "0001":
                decode0001(ctx,msgs);
                break;
            //终端登录
            case "0002":
                decode0002(ctx,msgs);
                break;
            //终端上报数据
            case "0003":
                decode0003(ctx,msgs);
                break;
        }

    }

    /**
     * 实时数据传输
     * @param ctx
     * @param msgs
     */
    private void decode0003(ChannelHandlerContext ctx, String msgs) throws Exception {

        System.out.println("0003实时数据传输指令解码");

        Decoder0003 decoder0003 = new Decoder0003();

        decoder0003.setOutMsg(msgs);

        super.channelRead(ctx,decoder0003);

    }

    /**
     * 终端登录
     * @param ctx
     * @param body
     */
    private void decode0002(ChannelHandlerContext ctx, String body) throws Exception {

        System.out.println("0002终端登录指令解码");
        //对鉴权码进行验证
        Decoder0002 decoder0002 = null;
        if(body.substring(14,22).equals("55AA55AA")){
            //验证通过采用平台通用应答
            decoder0002 = new Decoder0002();
            decoder0002.setXxid(body.substring(2,6));
            decoder0002.setSbid(body.substring(6,10));
            decoder0002.setAuthenticationCode(body.substring(14,22));
        }else{
            decoder0002 = new Decoder0002();
            decoder0002.setXxid(body.substring(2,6));
            decoder0002.setSbid(body.substring(6,10));
            decoder0002.setAuthenticationCode("");
        }

        super.channelRead(ctx,decoder0002);
    }



    //终端通用应答解析
    private void decode0001(ChannelHandlerContext ctx,String body) throws Exception {

        System.out.println("0001终端通用应答指令解码");

        Decoder0001 decoder0001 = new Decoder0001();

        //截取消息体中的应答iD和应答结果
        //此处截取的参数与PREFIX前缀有关
        decoder0001.setYdId(body.substring(8,12));
        decoder0001.setYdJg(body.substring(12,14));

        super.channelRead(ctx, decoder0001);
    }

    /**
     * 设备断开
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("设备断开连接："+CachePacket.ctxStrMap.get(ctx.channel()));
        CachePacket.strCtxMap.remove(CachePacket.ctxStrMap.get(ctx.channel()));
        CachePacket.ctxStrMap.remove(ctx.channel());

    }
}
