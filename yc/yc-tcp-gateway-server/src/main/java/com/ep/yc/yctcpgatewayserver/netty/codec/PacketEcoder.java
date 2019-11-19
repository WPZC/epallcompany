package com.ep.yc.yctcpgatewayserver.netty.codec;

import com.ep.yc.yctcpgatewayserver.netty.packetentity.Packet;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.ecoder.Platform7002;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.ecoder.ResEcoder0001;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.ecoder.ResEcoder0002;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.ecoder.ResEcoder0003;
import com.ep.yc.yctcpgatewayserver.netty.utils.HexStringUtils;
import com.ep.yc.yctcpgatewayserver.sendanaylsis.SendDbBase;
import com.ep.yc.yctcpgatewayserver.sendanaylsis.SendMsg;
import com.ep.yc.yctcpgatewayserver.sendanaylsis.entity.YcSendMsgEntity;
import com.ep.yc.yctcpgatewayserver.utils.HexByte;
import com.ep.yc.yctcpgatewayserver.utils.Inspection;
import com.ep.yc.yctcpgatewayserver.utils.bean.SpringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.logging.Logger;

/**
 * 响应编码器
 * @Author WQY
 * @Date 2019/11/11 14:43
 * @Version 1.0
 */
public class PacketEcoder extends MessageToByteEncoder<Packet> {

    private static final Logger logger = Logger.getLogger(PacketEcoder.class.getName());

    @Autowired
    SendMsg sendToAnaylsis = SpringUtils.getApplicationContext().getBean(SendMsg.class);
    @Autowired
    SendDbBase sendDbBase = SpringUtils.getApplicationContext().getBean(SendDbBase.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf byteBuf) throws Exception {

        if(packet instanceof ResEcoder0001){
            //平台通用应答回复
            System.out.println("平台通用应答write编码");
            ResEcoder0001 resEcoder0001 = (ResEcoder0001)packet;
            sendMsg(ctx,resEcoder0001.getYdId()+resEcoder0001.getYdRs()+resEcoder0001.getTime(),
                    "7001",resEcoder0001.getSbid());
        }else if(packet instanceof ResEcoder0003){
            System.out.println("实时数据write编码");
            ResEcoder0003 resEcoder0003 = (ResEcoder0003)packet;
            logger.info("实时数据write编码："+resEcoder0003.getOutMsg());
            sendToAnaylsis.sendToAnaylsis(resEcoder0003.getOutMsg());
        }else if(packet instanceof Platform7002){
            System.out.println("平台下发指令write编码");
            Platform7002 platform7002 = (Platform7002)packet;
            sendMsg(ctx,platform7002.getBody(),"7002",platform7002.getSbid());
            YcSendMsgEntity entity = new YcSendMsgEntity();
            entity.setDeviceNum(platform7002.getSbid());
            entity.setMsg(platform7002.getBody());
            entity.setSendTime(new Date());
            sendDbBase.saveSendMsg(entity);
        }
    }
    private void sendMsg(ChannelHandlerContext ctx,String msg,String xxid,String sbid){

        //标识位
        String top = "7E";
        //消息头
        String header = "";
        //长度,9为除了消息字节数以外的固定字节数
        String length = ""+((msg.length()/2)+9);
        header = xxid+sbid+HexByte.characterAi(length,"INTTOWORD");
        //校验从7E之后到校验码之前
        String crc = Inspection.getInspection(header+msg);
        //标识位
        String not = "7E";

        //凭借消息包
        String send = top+header+msg+crc+not;
        logger.info("平台下发："+send);
        byte[] b = HexStringUtils.hexStringToByte(send);
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(b);
        ctx.writeAndFlush(buf);
    }
}
