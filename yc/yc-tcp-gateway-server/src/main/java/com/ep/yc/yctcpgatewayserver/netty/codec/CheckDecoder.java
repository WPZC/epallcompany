package com.ep.yc.yctcpgatewayserver.netty.codec;

import com.ep.yc.yctcpgatewayserver.utils.Inspection;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

/**
 * 数据验证码及完整性校验
 * @Author WQY
 * @Date 2019/11/12 11:53
 * @Version 1.0
 */
public class CheckDecoder extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(CheckDecoder.class.getName());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String msgs = (String) msg;
        logger.info("数据验证码及完整性校验:"+msgs);
        if(msgs.length()<14){
            logger.info("长度不符合基本长度要求:"+msgs);
        }else{
            String prefix = msgs.substring(0,2);
            String suffix = msgs.substring(msgs.length()-2);
            //判断包首和包未是否是7E
            if((!prefix.equals("7E"))||(!suffix.equals("7E"))){
                logger.info("包首或包未不符合规范:"+msgs);
            }else{
                //校验码校验
                //获取校验码
                String checkCode = msgs.substring(msgs.length()-4,msgs.length()-2);
                //获取校验体
                String checkBody = msgs.substring(2,msgs.length()-4);
                String bodyCode = Inspection.getInspection(checkBody);
                if(checkCode.equals(bodyCode)){
                    super.channelRead(ctx, msg);
                }else{
                    logger.info("校验码错误:"+msgs);
                }
            }
        }
    }
}
