package com.ep.yc.yctcpgatewayserver.sendanaylsis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/9 14:43
 */
@Component
@FeignClient(value = "yc-gateway-mq")
public interface SendMsg {

//    @RequestMapping(value = "/test1",method = RequestMethod.GET)
//    String testFegin(@RequestParam("id") String id);
//
//    @RequestMapping(value = "/anaylsis/request_sb",method = RequestMethod.POST)
//    void sendToAnaylsis(@RequestParam("message") String message);

    @RequestMapping(value = "/receive/addMqMsg",method = RequestMethod.POST)
    void sendToAnaylsis(@RequestParam("msg") String msg);

}
