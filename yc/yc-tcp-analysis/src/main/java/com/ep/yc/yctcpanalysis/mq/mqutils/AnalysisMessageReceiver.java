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
 * analysis主消费者
 * @author WQY
 * @date 2019/9/4 10:19
 */
@Component
public class AnalysisMessageReceiver {

    public static final Logger log = LoggerFactory.getLogger(AnalysisMessageReceiver.class);

    private Long count = 0L;

    @Autowired
    private AnalysisMsgAll analysisMsgAll;

//    @Autowired
//    private TestBaseDB testBaseDB;

    @RabbitListener(queues = "yc.analysis.queue.main")
    public void receiveRealTimeMsg(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("收到realtimemsg：{}", msg);
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
            //拒收报错信息，并踢出主队列，加入延迟队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

            log.error("消息消费发生异常，error msg:{}", exception.getMessage(), exception);

        }
    }
}
