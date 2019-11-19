package com.ep.yc.ycview.controller;

import com.ep.yc.ycview.entity.*;
import com.ep.yc.ycview.service.DataMangementService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author WQY
 * @date 2019/9/18 18:18
 */
@Controller
@RequestMapping("/data")
@Api(value = "数据获取管理")
public class DataMangement {


    @Autowired
    private DataMangementService dataMangementService;

    private Gson gson = new Gson();


    /**
     * 获取该组织机构下的地图实时数据
     * @param orgCode
     * @return
     */
    @RequestMapping(value = "/getdeviceinfolist",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "获取该组织机构下的地图实时数据")
    @ApiImplicitParam(name = "orgCode",value = "组织机构代码",dataType = "String")
    public String getdeviceinfolist(String orgCode){

        OutView outView = new OutView();
        String rs;
        try {
            List<Object> ycDeviceInfoEntities = dataMangementService.getdeviceinfolist(orgCode);
            outView.setState(0);
            outView.setMsg(ycDeviceInfoEntities);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }


    /**
     * 获取地图实时数据（avg）
     * @param orgNum
     * @return
     */
    @RequestMapping(value = "/getavgrealtimeinfo",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "获取地图实时数据")
    @ApiImplicitParam(name = "orgNum",value = "组织机构代码",dataType = "String")
    public String getavgrealtimeinfo(String orgNum){

        OutView outView = new OutView();
        String rs;
        try {
            YcRealtimeinfoEntity ycRealtimeinfoEntity = dataMangementService.getavgrealtimeinfo(orgNum);
            outView.setState(0);
            outView.setMsg(ycRealtimeinfoEntity);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }


    /**
     * 获取图表数据
     * @return
     */
    @RequestMapping(value = "/findbydaymsg",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "获取图表数据")
    @ApiImplicitParam(name = "orgNum",value = "组织机构代码",dataType = "String")
    public String findByDayMsg(String orgNum){

        OutView outView = new OutView();
        List<Object> rs ;
        try {
            rs = dataMangementService.findByDayMsg(orgNum);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }

    /**
     * 获取所有历史数据
     * @param startTime
     * @param endTime
     * @param sb
     * @return
     */
    @RequestMapping(value = "/findAllByLs",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "获取所有历史数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "String"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "String"),
            @ApiImplicitParam(name = "sb",value = "设备编号",dataType = "String")
    })
    public String findAllByLs(String startTime,String endTime,String sb){

        OutView outView = new OutView();
        List<Object> rs ;
        try {
            rs = dataMangementService.findAllByLs(startTime,endTime,sb);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }

    /**
     * 获取实时数据列表
     * @param orgNum 组织机构编码
     * @return
     */
    @RequestMapping(value = "/getrealtimeinfo",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "获取实时数据列表")
    @ApiImplicitParam(name = "orgNum",value = "组织机构代码",dataType = "String")
    public String getrealtimeinfo(String orgNum){

        OutView outView = new OutView();
        List<Object> rs ;
        try {
            rs = dataMangementService.getrealtimeinfo(orgNum);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }


    /**
     * 获取视频列表
     * @param orgNum 组织机构编码
     * @return
     */
    @RequestMapping(value = "/getVideoUrl",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "获取视频列表")
    @ApiImplicitParam(name = "orgNum",value = "组织机构代码",dataType = "String")
    public String getVideoUrl(String orgNum){

        OutView outView = new OutView();
        String[] rs ;
        try {
            rs = dataMangementService.getVideoUrl(orgNum);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }


    /**
     * 获取报警数据列表
     * @param orgNum 组织机构编码
     * @return
     */
    @RequestMapping(value = "/getAlarminfoList",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String getAlarminfoList(String orgNum,String startTime,String endTime,String deviceNum){

        OutView outView = new OutView();
        List<YcAlarmInfoEntity> rs ;
        try {
            rs = dataMangementService.getAlarminfoList(orgNum,startTime,endTime,deviceNum);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }



    /**
     * 处理报警
     * @return
     */
    @RequestMapping(value = "/updateById",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String updateById(Long id, String msg,@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request){

        OutView outView = new OutView();
        String rs ;
        try {
            rs = dataMangementService.updateById(id,msg,file);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }


    /**
     * 根据设备编号查询实时数据
     * @return
     */
    @RequestMapping(value = "/getRealTimeInfoD",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String getRealTimeInfoD(String sbid){

        OutView outView = new OutView();
        List<YcRealtimeinfoEntity> rs ;
        try {
            rs = dataMangementService.getRealTimeInfoD(sbid);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }
    /**
     * 获取协议类型
     * @return
     */
    @RequestMapping(value = "/getAgreementTypes",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String getAgreementTypes(){

        OutView outView = new OutView();
        List<YcAgreementEntity> rs ;
        try {
            rs = dataMangementService.getAgreementTypes();
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }

    /**
     * 平台设置参数及控制
     * @return
     */
    @RequestMapping(value = "/setParams",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "平台设置参数及控制")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg",value = "json，key-value的消息体",dataType = "String",required = true),
            @ApiImplicitParam(name = "sbid",value = "设备编号",dataType = "String",required = true)
    })
    public OutView<String> setParams(String msg,String sbid){

        OutView outView = new OutView();
        String rs ;
        try {
            rs = dataMangementService.setParams(msg,sbid);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
            e.printStackTrace();
        }

        return outView;
    }

}
