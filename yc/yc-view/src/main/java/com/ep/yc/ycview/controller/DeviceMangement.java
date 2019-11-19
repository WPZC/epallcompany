package com.ep.yc.ycview.controller;

import com.ep.yc.ycview.entity.OutView;
import com.ep.yc.ycview.entity.YcDeviceInfoEntity;
import com.ep.yc.ycview.service.DeviceManagementService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author WQY
 * @date 2019/9/12 12:24
 */
@Controller
@RequestMapping("/device")
@Api(value = "设备数据管理")
public class DeviceMangement {


    @Autowired
    private DeviceManagementService deviceManagementService;

    private Gson gson = new Gson();

    @RequestMapping("/savedevice")
    @ResponseBody
    public String saveDevice(YcDeviceInfoEntity ycDeviceInfoEntity){
        OutView outView = new OutView();
        String rs;
        try {
            ycDeviceInfoEntity.setInputTime(new Date());
            rs = deviceManagementService.saveDeviceInfo(ycDeviceInfoEntity);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
        }

        return gson.toJson(outView);
    }

//    @RequestMapping("/updatedevice")
//    @ResponseBody
//    public String updateDevice(YcDeviceInfoEntity ycDeviceInfoEntity){
//        OutView outView = new OutView();
//        String rs;
//        try {
//            ycDeviceInfoEntity.setInputTime(new Date());
//            rs = deviceManagementService.saveDeviceInfo(ycDeviceInfoEntity);
//            outView.setState(0);
//            outView.setMsg(rs);
//        } catch (Exception e) {
//            outView.setState(1);
//            outView.setMsg("出现异常");
//        }
//
//        return gson.toJson(outView);
//    }


    @RequestMapping("/deletebydevicenum")
    @ResponseBody
    public String deleteByDeviceNum(String deviceNum){
        OutView outView = new OutView();
        String rs;
        try {
            rs = deviceManagementService.deleteByDeviceNum(deviceNum);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
        }

        return gson.toJson(outView);
    }


    /**
     * 获取该组织下的所有设备
     * @param orgCode
     * @return
     */
    @RequestMapping("/getdevicelist")
    @ResponseBody
    public String getdevicelist(String orgCode){
        OutView outView = new OutView();
        List<YcDeviceInfoEntity> rs;
        try {
            rs = deviceManagementService.getdevicelist(orgCode);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
        }

        return gson.toJson(outView);
    }


    @RequestMapping("/findByDeviceNum")
    @ResponseBody
    public String findByDeviceNum(String deviceNum){
        OutView outView = new OutView();
        YcDeviceInfoEntity rs;
        try {
            rs = deviceManagementService.findByDeviceNum(deviceNum);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }


    @RequestMapping("/updateDeviceInfo")
    @ResponseBody
    public String findByDeviceNum(YcDeviceInfoEntity ycDeviceInfoEntity){
        OutView outView = new OutView();
        String rs;
        try {
            ycDeviceInfoEntity.setInputTime(new Date());
            rs = deviceManagementService.updateDeviceInfo(ycDeviceInfoEntity);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }


}
