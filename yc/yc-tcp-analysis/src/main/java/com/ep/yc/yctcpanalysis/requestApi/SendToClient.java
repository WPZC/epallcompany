package com.ep.yc.yctcpanalysis.requestApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/12 11:17
 */
@Component
@FeignClient(value = "ep-yc-tcp-client")
public interface SendToClient {

    @RequestMapping(value = "client/receive",method = RequestMethod.POST)
    String sendToClient(@RequestParam("message") String message);

}
