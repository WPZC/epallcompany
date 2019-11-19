package com.ep.yc.yctcpanalysis.requestApi;

import com.ep.yc.yctcpanalysis.entity.IconEntity;
import com.ep.yc.yctcpanalysis.entity.YcAlarmInfoEntity;
import com.ep.yc.yctcpanalysis.entity.YcDeviceInfoEntity;
import com.ep.yc.yctcpanalysis.entity.YcRealtimeinfoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/12 11:17
 */
@Component
@FeignClient(value = "yc-db-base")
public interface DbUtil {

    @RequestMapping(value = "/anaylsisToDb/save_info",method = RequestMethod.POST)
    int save_info(@RequestBody YcRealtimeinfoEntity ycRealtimeinfoEntity);

    @RequestMapping(value = "/anaylsisToDb/save_info_ls",method = RequestMethod.POST)
    int save_info_ls(@RequestBody YcRealtimeinfoEntity ycRealtimeinfoEntity);

    @RequestMapping(value = "/anaylsisToDb/save",method = RequestMethod.GET)
    String save(@RequestParam("name") String name);

    @RequestMapping(value = "/anaylsisToDb/findByDeviceNum",method = RequestMethod.POST)
    YcDeviceInfoEntity findByDeviceNum(@RequestParam("deviceNum") String deviceNum);

    @RequestMapping(value = "/db_w/saveAlarmInfo",method = RequestMethod.POST)
    String saveAlarmInfo(@RequestBody YcAlarmInfoEntity ycAlarmInfoEntity);

    @RequestMapping(value = "/db_w/findByDayMsgA",method = RequestMethod.POST)
    String findByDayMsgA(@RequestParam("sbNum") String sbNum);

    @RequestMapping(value = "/db_w/getAllDevicePlatform",method = RequestMethod.POST)
    String getAllDevicePlatform();

    /**
     * 获取设备列表
     * @return
     */
    @RequestMapping(value = "/db_w/getDeviceList",method = RequestMethod.POST)
    String getDeviceList();

    /**
     * 获取对接平台列表
     * @return
     */
    @RequestMapping(value = "/db_w/gettoplatformlist",method = RequestMethod.POST)
    String gettoplatformlist();

    /**
     * 根据mn查询
     * @return
     */
    @RequestMapping(value = "/db_w/findByDeviceMn",method = RequestMethod.POST)
    String findByDeviceMn(@RequestParam("mn") String mn);



}
