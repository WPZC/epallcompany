package com.ep.yc.ycview.requestdb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author WQY
 * @date 2019/9/21 16:01
 */
@Component
@FeignClient(value = "ep-yc-tcp-client")
public interface SendCloent {

    /**
     * 启动client
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping(value = "/client/startClient", method = RequestMethod.POST)
    String startClient(@RequestParam("ip") String ip,@RequestParam("port") Integer port);

    /**
     * 关闭客户端
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping(value = "/client/shutDownClient", method = RequestMethod.POST)
    String shutDownClient(@RequestParam("ip") String ip,@RequestParam("port") Integer port);

}
