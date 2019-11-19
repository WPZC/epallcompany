package com.ep.yc.yctcpanalysis.mq.mqutils;

import com.ep.yc.yctcpanalysis.service.AnalysisMsgAll;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 死信消费者，如果此次消费失败则发送给失败数据库
 * @author WQY
 * @date 2019/9/4 10:43
 */
@Component
public class DeadLetterMessageReceiver {

    public static final Logger log = LoggerFactory.getLogger(DeadLetterMessageReceiver.class);

    @Autowired
    private AnalysisMsgAll analysisMsgAll;

    @RabbitListener(queues = "yc.analysis.queue.death")
    public void receiveRealTimeMsg(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("死信队列收到realtimemsg：{}", msg);
        boolean ack = true;
        Exception exception = null;
        try {
            analysisMsgAll.acceptedAnalysisMsg(msg);
        } catch (Exception e){
            ack = false;
            exception = e;
        }

        //判断ack是否报错,出错则拒收消息
        if (ack){
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } else {
            //拒收报错信息，存入失败数据库
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            log.error("消息消费发生异常，error msg:{}", exception.getMessage(), exception);

        }
    }

}
