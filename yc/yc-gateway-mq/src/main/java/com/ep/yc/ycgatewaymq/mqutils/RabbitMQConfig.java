package com.ep.yc.ycgatewaymq.mqutils;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息队列配置
 * @author WQY
 * @date 2019/9/3 15:15
 */
@Configuration
public class RabbitMQConfig {

    /*-------------------声明队列和交换机START-----------------------*/
    //analysis交换机
    public static final String ANALYSIS_EXCHANGE = "yc.analysis.exchange";
    //analysis主队列
    public static final String ANALYSIS_QUEUE_MAIN = "yc.analysis.queue.main";
    //analysis死信交换机
    public static final String ANALYSIS_EXCHANGE_DEATH = "yc.analysis.exchange.death";
    //analysis死信延时队列
    public static final String ANALYSIS_QUEUE_DEAD_DELAY = "yc.analysis.queue.dead.delay";
    //analysis死信队列
    public static final String ANALYSIS_QUEUE_DEATH = "yc.analysis.queue.death";
    /*-------------------声明队列和交换机END-----------------------*/

    /*-------------------声明路由参数START-----------------------*/
    //主队列路由
    public static final String ANALYSIS_MAIN_ROUT = "yc.analysis.main";
    //死信延时队列路由
    public static final String ANALYSIS_DEAD_DELAY_ROUT = "yc.analysis.dead.delay";
    //死信队列路由
    public static final String ANALYSIS_DEATH_ROUT = "yc.analysis.death";
    /*-------------------声明路由参数END-----------------------*/
    /**
     * 声明analysis交换机
     * @return
     */
    @Bean("analysisExchange")
    public TopicExchange analysisExchange(){
        return new TopicExchange(ANALYSIS_EXCHANGE);
    }

    /**
     * 声明analysis死信交换机
     * @return
     */
    @Bean("analysisDeathExchange")
    public DirectExchange analysisDeathExchange(){
        return new DirectExchange(ANALYSIS_EXCHANGE_DEATH);
    }

    /**
     * 声明analysis主队列
     * @return
     */
    @Bean("analysisQueuemain")
    public Queue analysisQueuemain(){
        Map<String, Object> args = new HashMap<>(3);
        //设置十秒过期，过期后加入ANALYSIS_QUEUE_DEATH死信队列
        args.put("x-message-ttl", 10*1000);
        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", ANALYSIS_EXCHANGE_DEATH);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", ANALYSIS_DEAD_DELAY_ROUT);
        return QueueBuilder.durable(ANALYSIS_QUEUE_MAIN).withArguments(args).build();
    }

    /**
     * 声明analysis延时队列
     * @return
     */
    @Bean("analysisQueueDeadDelay")
    public Queue analysisQueueDeadDelay(){
        Map<String, Object> args = new HashMap<>(3);
        //设置十秒过期，过期后加入ANALYSIS_QUEUE_DEATH死信队列
        args.put("x-message-ttl", 10*1000);
        //x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", ANALYSIS_EXCHANGE_DEATH);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", ANALYSIS_DEATH_ROUT);
        return QueueBuilder.durable(ANALYSIS_QUEUE_DEAD_DELAY).withArguments(args).build();
    }


    /**
     * 声明analysis延时队列
     * @return
     */
    @Bean("analysisQueueDeath")
    public Queue analysisQueueDeath(){
        Map<String, Object> args = new HashMap<>(1);
        //设置十秒过期
        args.put("x-message-ttl", 10*1000);
        return QueueBuilder.durable(ANALYSIS_QUEUE_DEATH).withArguments(args).build();
    }

    /**
     * analysis交换机与主队列进行绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding analysisExchangeBindingMain(@Qualifier("analysisQueuemain") Queue queue,
                                               @Qualifier("analysisExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("yc.analysis.main");
    }

    /**
     * analysis交换机与延时队列进行绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding analysisExchangeBindingDelay(@Qualifier("analysisQueueDeadDelay") Queue queue,
                                               @Qualifier("analysisDeathExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("yc.analysis.dead.delay");
    }

    /**
     * analysis死信交换机与延时队列进行绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding analysisExchangeDeathBindingDeathQueue(@Qualifier("analysisQueueDeath") Queue queue,
                                                @Qualifier("analysisDeathExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("yc.analysis.death");
    }

}
