package com.ep.yc.ycgatewaymq.mqutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author WQY
 * @date 2019/9/3 15:51
 */
@Component
public class AnalysisMessageSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback, InitializingBean {

    public static final Logger log = LoggerFactory.getLogger(AnalysisMessageSender.class);


    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Long count = 0L;
    /**
     * 往解析主队列发送数据
     * @param msg
     */
    public void sendAnalysisMainMsg(String msg){

        Message message = MessageBuilder.withBody(msg.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8")
                .setMessageId(UUID.randomUUID()+"").build();
        // 自定义消息唯一标识
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(message.getMessageProperties().getMessageId());
        count++;
        System.out.println("计数器值为："+count);
        System.out.println("时间戳："+ System.currentTimeMillis());
        rabbitTemplate.convertSendAndReceive(RabbitMQConfig.ANALYSIS_EXCHANGE, RabbitMQConfig.ANALYSIS_MAIN_ROUT, message,correlationData);
    }

    /**
     * 用于实现消息发送到RabbitMQ交换器后接收ack回调。
     * 如果消息发送确认失败就进行重试。
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        // 消息回调确认失败处理
        if (!ack) {
            // 这里以做消息的从发等处理
            System.out.println("消息发送失败，消息ID:"+correlationData.getId());
        } else {
            System.out.println("消息发送成功,消息ID:"+correlationData.getId()+"===ack:"+ack);
        }
    }

    /**
     * 用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调。
     * 发生脑裂的时候会发生这种情况
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("MQ消息发送失败，replyCode:{}, replyText:{}，exchange:{}，routingKey:{}，消息体:{}",
                replyCode, replyText, exchange, routingKey, message.getBody());

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }
}
