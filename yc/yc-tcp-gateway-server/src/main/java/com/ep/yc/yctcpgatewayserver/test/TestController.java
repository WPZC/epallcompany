package com.ep.yc.yctcpgatewayserver.test;

import com.ep.yc.yctcpgatewayserver.sendanaylsis.SendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/9 14:46
 */
@RestController
public class TestController {

    @Autowired
    private SendMsg sendMsg;

    @GetMapping("/testc")
    @ResponseBody
    public String testc(String id){
        return null;//sendMsg.testFegin(id);
    }

}
