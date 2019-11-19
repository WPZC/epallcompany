package com.ep.yc.ycgatewaymq.external;

import com.ep.yc.ycgatewaymq.mqutils.AnalysisMessageSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author WQY
 * @Date 2019/11/16 16:29
 * @Version 1.0
 */
@Api(value = "MQ接收数据接口")
@RestController
@RequestMapping("/receive")
public class ReceiveInterface {

    @Autowired
    private AnalysisMessageSender analysisMessageSender;

    @RequestMapping(value = "/addMqMsg",method = RequestMethod.POST)
    @ApiImplicitParam(name = "msg",value = "数据体",required = true,dataType = "String")
    @ApiOperation(value = "将消息体传入消息队列")
    public void addMqMsg(String msg){

        analysisMessageSender.sendAnalysisMainMsg(msg);

    }

}
