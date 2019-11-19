package com.epyc.ycdbbase.db_w.exposure;

import com.epyc.ycdbbase.db_w.dao.*;
import com.epyc.ycdbbase.db_w.entity.IconEntity;
import com.epyc.ycdbbase.entity.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xalan.internal.xsltc.dom.SAXImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author WQY
 * @date 2019/9/9 15:30
 */
@RestController
@RequestMapping("/db_w")
public class ViewController {

    @Autowired
    private  Yc_User_Jpa yc_user_jpa;
    @Autowired
    private Yc_Organization_Jpa yc_organization_jpa;
    @Autowired
    private Yc_Device_Jpa yc_device_jpa;
    @Autowired
    private Yc_Platform_Jpa yc_platform_jpa;
    @Autowired
    private Yc_Device_Realtime_Jpa yc_device_realtime;
    @Autowired
    private Icon_Jpa icon_jpa;
    @Autowired
    private Yc_Alarminfo_Jpa yc_alarminfo_jpa;
    @Autowired
    private Yc_RealTime_Jpa yc_realTime_jpa;
    @Autowired
    private Yc_Device_PlatformJpa yc_device_platformJpa;
    @Autowired
    private Yc_AgreementJpa yc_agreementJpa;
    @Autowired
    private Yc_Send_Msg_Jpa yc_send_msg_jpa;


    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public String Login(@RequestParam("username") String username,@RequestParam("password") String password){
        YcUserEntity ycUserEntity = yc_user_jpa.findByUsernameAndPassword(username,password);
        if(ycUserEntity==null){
            return "Failure";
        }else{
            return gson.toJson(ycUserEntity);
        }
    }
    /*-----------------用户管理区域START-------------------------*/
    /**
     * 添加用户
     * @param ycUserEntity
     * @return
     */
    @PostMapping("/adduser")
    @ResponseBody
    public String addUser(@RequestBody YcUserEntity ycUserEntity){

        YcUserEntity userEntity = yc_user_jpa.findByUsername(ycUserEntity.getUsername());

        if(userEntity==null){
            ycUserEntity.setSavetime(new Date());
            yc_user_jpa.save(ycUserEntity);
            return "添加成功";
        }else{
            return "用户名已存在";
        }

    }

    /**
     * 删除用户
     * @param username
     * @return
     */
    @PostMapping("/deleteuser")
    @ResponseBody
    public String deleteuser(@RequestParam("username") String username){

        int rs = yc_user_jpa.deleteByUsername(username);

        if(rs==1){
            return "SUCCESS";
        }else{
            return "Failure";
        }

    }

    /**
     * 获取用户列表
     * @param orgnum
     * @return
     */
    @PostMapping("/getuserlist")
    @ResponseBody
    public String getuserlist(@RequestParam("orgnum") String orgnum){

        List<YcUserEntity> list = yc_user_jpa.findByOrganizationNumList(orgnum);

        return gson.toJson(list);

    }
    /**
     * 获取组织机构信息
     * @return
     */
    @PostMapping("/getOrganizationList")
    @ResponseBody
    public String getOrganizationList(){

        List<YcOrganizationEntity> ycOrganizationEntities = yc_organization_jpa.findAll();

        return gson.toJson(ycOrganizationEntities);
    }

    /**
     * 添加组织机构
     * @return
     */
    @PostMapping("/addOrganization")
    @ResponseBody
    public String addOrganization(@RequestBody YcOrganizationEntity ycOrganizationEntity){

        YcOrganizationEntity rs = yc_organization_jpa.save(ycOrganizationEntity);

        return gson.toJson(rs);
    }

    /**
     * 删除组织机构
     * @return
     */
    @PostMapping("/deleteOrgNum")
    @ResponseBody
    public String deleteOrgNum(@RequestParam("orgNum") String orgNum){

        int rs = 0;
        try {
            //先删除用户
            rs=yc_user_jpa.deleteLikeOrganizationName(orgNum);
        }catch (Exception e){
            return "清除组织机构下用户失败";
        }
        try {
            //在删除设备
            rs=yc_device_jpa.deleteLikeOrganizationName(orgNum);
        }catch (Exception e){
            return "清除组织机构下设备";
        }
        try {
            //最后删除组织机构
            rs=yc_organization_jpa.deleteLikeOrganizationName(orgNum);
        }catch (Exception e){
            return "清除组织机构失败";
        }

        return "删除成功";


    }

    /**
     * 获取此组织编码下的所有组织
     * @return
     */
    @PostMapping("/findOrganizationBynum")
    @ResponseBody
    public String findOrganizationBynum(@RequestParam("num") String num){

        List<YcOrganizationEntity> ycOrganizationEntities = yc_organization_jpa.findLikeOrganizationName(num);

        return gson.toJson(ycOrganizationEntities);
    }
    /*-----------------用户管理区域END-------------------------*/

    /*-----------------设备管理区域START-------------------------*/
    /**
     * 添加设备
     * @return
     */
    @PostMapping("/savedeviceinfo")
    @ResponseBody
    public String saveDeviceInfo(@RequestBody YcDeviceInfoEntity ycDeviceInfoEntity){

        YcDeviceInfoEntity rs = yc_device_jpa.findByDeviceNum(ycDeviceInfoEntity.getDeviceNum());

        if(rs!=null){
            return "设备已存在";
        }else{
            rs = yc_device_jpa.save(ycDeviceInfoEntity);

            if(rs == null){
                return "添加失败";
            }else{
                return "添加成功";
            }
        }
    }

    /**
     * 添加设备
     * @return
     */
    @PostMapping("/updateDeviceInfo")
    @ResponseBody
    public String updateDeviceInfo(@RequestBody YcDeviceInfoEntity ycDeviceInfoEntity){

        YcDeviceInfoEntity rs = yc_device_jpa.save(ycDeviceInfoEntity);

        if(rs == null){
            return "修改失败";
        }else{
            return "修改成功";
        }
    }
    /**
     * 根据设备号删除设备
     * @return
     */
    @PostMapping("/deletebydevicenum")
    @ResponseBody
    public String deletebyDeviceNum(@RequestParam("deviceNum") String deviceNum){
        int rs = yc_device_jpa.deleteByDeviceNum(deviceNum);
        if(rs==0){
            return "删除失败";
        }else{
            return "删除成功";
        }
    }

    /**
     * 根据设备号删除设备
     * @return
     */
    @PostMapping("/findByDeviceNum")
    @ResponseBody
    public String findByDeviceNum(@RequestParam("deviceNum") String deviceNum){
        YcDeviceInfoEntity rs = yc_device_jpa.findByDeviceNum(deviceNum);
        return gson.toJson(rs);

    }

    /**
     * 获取设备列表
     * @return
     */
    @PostMapping("/getdevicelist")
    @ResponseBody
    public String getDeviceList(@RequestParam("orgCode") String orgCode){
        List<YcDeviceInfoEntity> rs = yc_device_jpa.findByLikeOrOrganizationNum(orgCode+"%");

        return gson.toJson(rs);

    }
    /*-----------------设备管理区域END-------------------------*/

    /*-----------------对接平台管理START-------------------------*/
    /**
     * 添加对接平台
     * @return
     */
    @PostMapping("/addtoplatform")
    @ResponseBody
    public String addToPlatform(@RequestBody YcPlatformInfoEntity ycPlatformInfoEntity){

        ycPlatformInfoEntity.setState(1L);
        YcPlatformInfoEntity platformInfoEntity =yc_platform_jpa.findByToPlatformName(ycPlatformInfoEntity.getToPlatformName());

        if(platformInfoEntity==null){
            yc_platform_jpa.save(ycPlatformInfoEntity);
            return "保存成功";
        }else{
            return "对接平台名称已存在";
        }
    }

    /**
     * 删除对接平台
     * @return
     */
    @PostMapping("/deletetoplatform")
    @ResponseBody
    public String deleteToPlatform(@RequestParam("platform") String platform){

        int rs = yc_platform_jpa.deleteByToPlatformName(platform);
        if(rs==0){
            return "删除失败";
        }else{
            return "删除成功";
        }

    }

    /**
     * 修改对接平台state
     * @return
     */
    @PostMapping("/updatePlatformState")
    @ResponseBody
    public String updatePlatformState(@RequestParam("state") Long state,@RequestParam("ip") String ip,@RequestParam("port") String port){

        int rs = yc_platform_jpa.updatePlatformState(state,port,ip);
        if(rs==0){
            return "更新对接平台状态成功";
        }else{
            return "更新对接平台状态失败";
        }

    }
    /**
     * 获取对接平台列表
     * @return
     */
    @PostMapping("/gettoplatformlist")
    @ResponseBody
    public String getToPlatformList(){
        List<YcPlatformInfoEntity> rs = yc_platform_jpa.findAll();
        return gson.toJson(rs);

    }
    /*-----------------对接平台管理END-------------------------*/
    /*-----------------获取数据管理Start-------------------------*/
    /**
     * 根据设备号获取实时数据
     * @return
     */
    @PostMapping("/getRealTimeInfoD")
    @ResponseBody
    public String getRealTimeInfoD(@RequestParam("deviceNum") String deviceNum){

        YcRealtimeinfoEntity rs = yc_realTime_jpa.findByYdiDeviceNum(deviceNum);

        return gson.toJson(rs);

    }
    /**
     * 获取该组织机构下的所有设备
     * @return
     */
    @PostMapping("/getdeviceinfolist")
    @ResponseBody
    public String getDeviceInfoList(@RequestParam("orgNum") String orgNum){

        List<YcDeviceInfoRealtimeEntity> rs = yc_device_realtime.findByOrganizationNumList(orgNum+"%");


        return gson.toJson(rs);

    }

    /**
     * 获取地图平均实时数据
     * @param orgNum 组织机构编码
     * @return
     */
    @PostMapping("/getavgrealtimeinfo")
    @ResponseBody
    public String getAvgRealtimeInfo(@RequestParam("orgNum") String orgNum){

        List<YcDeviceInfoRealtimeEntity> rs = yc_device_realtime.findByOrganizationNumList(orgNum+"%");

        YcRealtimeinfoEntity ycRealtimeinfoEntity = new YcRealtimeinfoEntity();
        if(rs==null||rs.size()==0){
            return gson.toJson(ycRealtimeinfoEntity);
        }


        double pm25Total = 0;
        double pm10Total = 0;
        double noise = 0;
        double airTemperatureTotal = 0;
        double airHumidity = 0;
        double windSpeedTotal = 0;
        double tsp = 0;
        double atmosphericPressure = 0;
        int num = rs.size();
        for (int i = 0;i<num;i++){
            pm25Total+=Double.parseDouble(rs.get(i).getPm25());
            pm10Total+=Double.parseDouble(rs.get(i).getPm10());
            noise+=Double.parseDouble(rs.get(i).getNoise());
            airTemperatureTotal+=Double.parseDouble(rs.get(i).getAirTemperature());
            airHumidity+=Double.parseDouble(rs.get(i).getAirHumidity());
            windSpeedTotal+=rs.get(i).getWindSpeed();
            tsp+=Double.parseDouble(rs.get(i).getTsp());
            atmosphericPressure+=Double.parseDouble(rs.get(i).getAtmosphericPressure());
        }
        DecimalFormat df = new DecimalFormat("#.0");
        ycRealtimeinfoEntity.setPm25(df.format(pm25Total/num));
        ycRealtimeinfoEntity.setPm10(df.format(pm10Total/num));
        ycRealtimeinfoEntity.setNoise(df.format(noise/num));
        ycRealtimeinfoEntity.setAirHumidity(df.format(airHumidity/num));
        ycRealtimeinfoEntity.setAirTemperature(df.format(airTemperatureTotal/num));
        ycRealtimeinfoEntity.setAtmosphericPressure(df.format(atmosphericPressure/num));
        ycRealtimeinfoEntity.setTsp(df.format(tsp/num));
        ycRealtimeinfoEntity.setWindSpeed(windSpeedTotal/num);
        ycRealtimeinfoEntity.setWindDirection(rs.get(0).getWindDirection());

        return gson.toJson(ycRealtimeinfoEntity);

    }


    /**
     * 获取实时数据列表
     * @param orgNum 组织机构编码
     * @return
     */
    @PostMapping("/getrealtimeinfo")
    @ResponseBody
    public String getRealTimeinfo(@RequestParam("orgNum") String orgNum){

        List<YcDeviceInfoRealtimeEntity> rs = yc_device_realtime.findByOrganizationNumList(orgNum+"%");

        return gson.toJson(rs);

    }


    /**
     * 获取曲线图数值
     * @return
     */
    @PostMapping("/findByDayMsg")
    @ResponseBody
    public String findByDayMsg(@RequestParam("orgNum") String orgNum){

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        List<IconEntity> rs = icon_jpa.findByDayMsg("yc_his"+format.format(new Date()),orgNum+"%");

        return gson.toJson(rs);

    }

    /**
     * 获取小时平均值
     * @return
     */
    @PostMapping("/findByDayMsgA")
    @ResponseBody
    public String findByDayMsgA(@RequestParam("sbNum") String sbNum){

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        IconEntity rs = icon_jpa.findByDayMsgA("yc_his"+format.format(new Date()),sbNum);

        return gson.toJson(rs);

    }

    /**
     * 获报警数据
     * @return
     */
    @PostMapping("/getalarminfolist")
    @ResponseBody
    public String getAlarminfoList(@RequestParam("orgnum") String orgnum){

        List<YcAlarmInfoEntity> rs = yc_alarminfo_jpa.findByOrgNum(orgnum+"%");

        return gson.toJson(rs);

    }

    /**
     * 获报警数据
     * @return
     */
    @PostMapping("/findByOrgNumAndStartTimeAndEndTimeList")
    @ResponseBody
    public String findByOrgNumAndStartTimeAndEndTimeList(@RequestParam("orgnum") String orgnum,@RequestParam("startTime") String startTime,
                                                         @RequestParam("endTime") String endTime,@RequestParam("deviceNum") String deviceNum){

        List<YcAlarmInfoEntity> rs = yc_alarminfo_jpa.findByOrgNumAndStartTimeAndEndTimeList(orgnum+"%",startTime,endTime,deviceNum);

        return gson.toJson(rs);

    }

    /**
     * 处理报警数据
     * @return
     */
    @PostMapping("/updateById")
    @ResponseBody
    public String updateById(@RequestParam("id") long id,@RequestParam("msg") String msg,@RequestParam("pic") String pic){

        int rs = yc_alarminfo_jpa.updateById(msg,id,pic);

        return rs+"";

    }
    /*-----------------获取数据管理End-------------------------*/
    /*-----------------报警数据START-------------------------*/
    /**
     * 获取该组织机构下的所有设备
     * @return
     */
    @PostMapping("/saveAlarmInfo")
    @ResponseBody
    public String saveAlarmInfo(@RequestBody YcAlarmInfoEntity ycAlarmInfoEntity){

        YcAlarmInfoEntity rs = yc_alarminfo_jpa.save(ycAlarmInfoEntity);

        if(rs!=null){
            return "保存成功";
        }else{
            return "保存失败";
        }



    }
    /*-----------------报警数据END-------------------------*/

    /*-----------------通讯数据START-------------------------*/

    /**
     * 获取该组织机构下的所有设备
     * @return
     */
    @PostMapping("/getAllDevicePlatform")
    @ResponseBody
    public String getAllDevicePlatform(){

        List<YcDevicePlatformEntity> rs = yc_device_platformJpa.findAll();

        return gson.toJson(rs);

    }

    /**
     * 获取协议类型
     * @return
     */
    @PostMapping("/getAgreementTypes")
    @ResponseBody
    public String getAgreementTypes(){

        List<YcAgreementEntity> rs = yc_agreementJpa.findAll();

        return gson.toJson(rs);

    }

    /**
     * 获取设备列表
     * @return
     */
    @PostMapping("/getDeviceList")
    @ResponseBody
    public String getDeviceList(){

        List<YcDeviceInfoEntity> rs = yc_device_jpa.findAll();

        return gson.toJson(rs);

    }


    /**
     * 根据MN查询
     * @return
     */
    @PostMapping("/findByDeviceMn")
    @ResponseBody
    public String findByDeviceMn(@RequestParam("mn") String mn){

        YcDeviceInfoEntity rs = yc_device_jpa.findByDeviceMn(mn);

        return gson.toJson(rs);

    }

    /**
     * 根据MN查询
     * @return
     */
    @PostMapping("/saveSendMsg")
    public void saveSendMsg(@RequestBody YcSendMsgEntity ycSendMsgEntity){

        YcSendMsgEntity rs = yc_send_msg_jpa.save(ycSendMsgEntity);

    }
    /*-----------------通讯数据END-------------------------*/
}
