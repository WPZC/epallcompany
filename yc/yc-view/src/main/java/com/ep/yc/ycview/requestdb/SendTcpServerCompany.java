package com.ep.yc.ycview.requestdb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author WQY
 * @Date 2019/11/15 14:48
 * @Version 1.0
 */
@Component
@FeignClient(value = "ep-yc-tcp-gateway-company")
public interface SendTcpServerCompany {

    /**
     * 平台下发控制和设置参数
     * @param msg
     * @param sbid
     * @return
     */
    @RequestMapping(value = "/tcpservercompany/setParams", method = RequestMethod.POST)
    String setParams(@RequestParam("msg") String msg, @RequestParam("sbid") String sbid);

}
