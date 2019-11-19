package com.epyc.yctcpclient.requestdb;


import com.epyc.yctcpclient.entity.YcPlatformInfoEntity;
import com.epyc.yctcpclient.entity.YcRealtimeinfoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author WQY
 * @date 2019/9/9 15:36
 */
@Component
@FeignClient(value = "yc-db-base")
public interface SendDbBase {

    /**
     * 获取对接平台列表
     * @return
     */
    @RequestMapping(value = "/db_w/gettoplatformlist",method = RequestMethod.POST)
    String gettoplatformlist();
    /*------------------对接平台管理END--------------------*/

    @RequestMapping(value = "/clientToDb/findAll",method = RequestMethod.POST)
    List<YcPlatformInfoEntity> findAll();

    @RequestMapping(value = "/clientToDb/findBySb",method = RequestMethod.POST)
    YcPlatformInfoEntity findBySb(@RequestParam("sb")String sb);

    @RequestMapping(value = "/db_w/updatePlatformState",method = RequestMethod.POST)
    String updatePlatformState(@RequestParam("state") Long state,@RequestParam("ip") String ip,@RequestParam("port") String port);
}
