package com.ep.yc.ycview.requestdb;

import com.ep.yc.ycview.entity.YcDeviceInfoEntity;
import com.ep.yc.ycview.entity.YcOrganizationEntity;
import com.ep.yc.ycview.entity.YcPlatformInfoEntity;
import com.ep.yc.ycview.entity.YcUserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author WQY
 * @date 2019/9/9 15:36
 */
@Component
@FeignClient(value = "yc-db-base")
public interface SendDbBase {


    /**
     * 登录请求
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/db_w/login", method = RequestMethod.POST)
    String isLogin(@RequestParam("username") String username, @RequestParam("password") String password);

    /**
     * 添加用户
     *
     * @param ycUserEntity
     * @return
     */
    @RequestMapping(value = "/db_w/adduser", method = RequestMethod.POST)
    String addUser(@RequestBody YcUserEntity ycUserEntity);

    /**
     * 获取用户列表
     *
     * @param orgnum
     * @return
     */
    @RequestMapping(value = "/db_w/getuserlist", method = RequestMethod.POST)
    String getuserlist(@RequestParam("orgnum") String orgnum);

    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping(value = "/db_w/deleteuser", method = RequestMethod.POST)
    String deleteUser(@RequestParam("username") String username);

    /**
     * 获取组织机构信息
     *
     * @return
     */
    @RequestMapping(value = "/db_w/getOrganizationList", method = RequestMethod.POST)
    String getOrganizationList();

    /**
     * 添加组织机构
     *
     * @return
     */
    @RequestMapping(value = "/db_w/addOrganization", method = RequestMethod.POST)
    String addOrganization(@RequestBody YcOrganizationEntity ycOrganizationEntity);

    /**
     * 获取组织机构信息
     *
     * @return
     */
    @RequestMapping(value = "/db_w/findOrganizationBynum", method = RequestMethod.POST)
    String findOrganizationBynum(@RequestParam("num") String num);

    /**
     * 删除组织机构
     *
     * @return
     */
    @RequestMapping(value = "/db_w/deleteOrgNum", method = RequestMethod.POST)
    String deleteOrgNum(@RequestParam("orgNum") String orgNum);

    /*------------------添加设备START--------------------*/

    /**
     * 添加设备
     *
     * @return
     */
    @RequestMapping(value = "/db_w/savedeviceinfo", method = RequestMethod.POST)
    String saveDeviceInfo(@RequestBody YcDeviceInfoEntity ycDeviceInfoEntity);

    /**
     * 添加设备
     *
     * @return
     */
    @RequestMapping(value = "/db_w/updateDeviceInfo", method = RequestMethod.POST)
    String updateDeviceInfo(@RequestBody YcDeviceInfoEntity ycDeviceInfoEntity);
    /**
     * 根据设备号查询
     *
     * @return
     */
    @RequestMapping(value = "/db_w/findByDeviceNum", method = RequestMethod.POST)
    String findByDeviceNum(@RequestParam("deviceNum") String deviceNum);

    /**
     * 添加组织机构
     *
     * @return
     */
    @RequestMapping(value = "/db_w/deletebydevicenum", method = RequestMethod.POST)
    String deleteByDevicenum(@RequestParam("deviceNum") String deviceNum);

    /**
     * 添加组织机构
     *
     * @return
     */
    @RequestMapping(value = "/db_w/getdevicelist", method = RequestMethod.POST)
    String getdevicelist(@RequestParam("orgCode") String orgCode);
    /*------------------添加设备END--------------------*/

    /*------------------对接平台管理START--------------------*/

    /**
     * 添加对接平台
     *
     * @return
     */
    @RequestMapping(value = "/db_w/addtoplatform", method = RequestMethod.POST)
    String addtoplatform(@RequestBody YcPlatformInfoEntity ycPlatformInfoEntity);

    /**
     * 添加对接平台
     *
     * @return
     */
    @RequestMapping(value = "/db_w/deletetoplatform", method = RequestMethod.POST)
    String deletetoplatform(@RequestParam("platform") String platform);

    /**
     * 获取对接平台列表
     *
     * @return
     */
    @RequestMapping(value = "/db_w/gettoplatformlist", method = RequestMethod.POST)
    String gettoplatformlist();
    /*------------------对接平台管理END--------------------*/


    /*------------------获取数据管理START--------------------*/

    /**
     * 获取组织机构下的所有设备
     *
     * @param orgNum
     * @return
     */
    @RequestMapping(value = "/db_w/getdeviceinfolist", method = RequestMethod.POST)
    String getdeviceinfolist(@RequestParam("orgNum") String orgNum);

    /**
     * 获取地图平均实时数据
     *
     * @param orgNum
     * @return
     */
    @RequestMapping(value = "/db_w/getavgrealtimeinfo", method = RequestMethod.POST)
    String getavgrealtimeinfo(@RequestParam("orgNum") String orgNum);

    /**
     * 获取每小时的平均数据
     *
     * @return
     */
    @RequestMapping(value = "/db_w/findByDayMsg", method = RequestMethod.POST)
    String findByDayMsg(@RequestParam("orgNum") String orgNum);

    /**
     * 获取所有历史数据
     * @param startTime
     * @param endTime
     * @param sb
     * @return
     */
    @RequestMapping(value = "/anaylsisToDb/findAllByLs", method = RequestMethod.POST)
    String findAllByLs(@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime, @RequestParam("sb") String sb);

    /**
     * 获取实时数据列表
     * @param orgNum
     * @return
     */
    @RequestMapping(value = "/db_w/getrealtimeinfo",method = RequestMethod.POST)
    String getrealtimeinfo(@RequestParam("orgNum") String orgNum);

    /**
     * 获取实时数据
     * @param deviceNum
     * @return
     */
    @RequestMapping(value = "/db_w/getRealTimeInfoD",method = RequestMethod.POST)
    String getRealTimeInfoD(@RequestParam("deviceNum") String deviceNum);

    /**
     * 获取报警数据
     * @param orgnum
     * @return
     */
    @RequestMapping(value = "/db_w/getalarminfolist",method = RequestMethod.POST)
    String getAlarminfoList(@RequestParam("orgnum") String orgnum);

    /**
     * 获取报警数据
     * @param orgnum
     * @return
     */
    @RequestMapping(value = "/db_w/findByOrgNumAndStartTimeAndEndTimeList",method = RequestMethod.POST)
    String findByOrgNumAndStartTimeAndEndTimeList(@RequestParam("orgnum") String orgnum,@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,@RequestParam("deviceNum") String deviceNum);

    /**
     * 处理报警数据
     * @return
     */
    @RequestMapping(value = "/db_w/updateById",method = RequestMethod.POST)
    String updateById(@RequestParam("id") long id,@RequestParam("msg") String msg,@RequestParam("pic") String pic);


    /**
     * 获取协议类型
     * @return
     */
    @RequestMapping(value = "/db_w/getAgreementTypes",method = RequestMethod.POST)
    String getAgreementTypes();

    /*------------------获取数据管理END--------------------*/

}
