package com.ep.yc.yctcpgatewayserver.sendanaylsis;

import com.ep.yc.yctcpgatewayserver.sendanaylsis.entity.YcSendMsgEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author WQY
 * @date 2019/9/9 15:36
 */
@Component
@FeignClient(value = "yc-db-base")
public interface SendDbBase {


    /**
     * 保存操作信息
     * @param ycSendMsgEntity
     * @return
     */
    @RequestMapping(value = "/db_w/saveSendMsg", method = RequestMethod.POST)
    String saveSendMsg(@RequestBody YcSendMsgEntity ycSendMsgEntity);

}
