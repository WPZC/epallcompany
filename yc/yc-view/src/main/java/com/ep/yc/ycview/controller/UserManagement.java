package com.ep.yc.ycview.controller;

import com.ep.yc.ycview.entity.*;
import com.ep.yc.ycview.service.UserManagementService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @author WQY
 * @date 2019/9/10 14:57
 */
@Controller
@RequestMapping("/user")
@Api(value = "用户管理")
public class UserManagement {

    private Gson gson = new Gson();


    @Autowired
    private UserManagementService userManagementService;

    /**
     * 添加用户
     * @param ycUserEntity
     * @return
     */
    @RequestMapping("/adduser")
    @ResponseBody
    public String addUser(YcUserEntity ycUserEntity){

        String rs = userManagementService.addUser(ycUserEntity);

        OutView outView = new OutView();

        try {
            outView.setState(0);
            outView.setMsg(rs);
            return gson.toJson(outView);
        }catch (Exception e){
            outView.setState(1);
            outView.setMsg(rs);
            return gson.toJson(outView);
        }
    }

    /**
     * 获取组织机构列表
     * @param orgnum
     * @return
     */
    @RequestMapping("/getorglist")
    @ResponseBody
    public String getOrgList(String orgnum){


        OutView outView = new OutView();
        try {
            List<YcUserEntity> rs = userManagementService.getuserlist(orgnum+"%");
            outView.setState(0);
            outView.setMsg(rs);
            return gson.toJson(outView);
        }catch (Exception e){
            outView.setState(1);
            outView.setMsg("获取失败");
            return gson.toJson(outView);
        }
    }


    /**
     * 删除用户
     * @param username
     * @return
     */
    @RequestMapping("/deleteuser")
    @ResponseBody
    public String deleteUser(String username,String deleteUsername){

        OutView outView = new OutView();

        //判断是否是当前用户
        if(deleteUsername.equals(username)){
            outView.setState(0);
            outView.setMsg("请勿删除自己");
            return gson.toJson(outView);
        }

        String rs = "";
        try {
            rs=userManagementService.deleteUser(deleteUsername);
            outView.setState(0);
            outView.setMsg(rs);
        }catch (Exception e){
            outView.setState(1);
            outView.setMsg(rs);
            e.printStackTrace();
        }

        return gson.toJson(outView);
    }



    /**
     * 获取组织机构列表
     * @return
     */
    @RequestMapping("/organizationlist")
    @ResponseBody
    public String organizationList(String username,String orgCode){

        OutView outView = new OutView();

        try {
            List<HashMap<String,Object>> map = userManagementService.findByOrganizationList(orgCode);
            outView.setState(0);
            outView.setMsg(map);
        }catch (Exception e){
            outView.setState(1);
            outView.setMsg("获取组织机构列表失败");
            e.printStackTrace();
        }
        return gson.toJson(outView);
    }


    /**
     * 获取平铺组织机构列表
     * @return
     */
    @RequestMapping("/getorglistpp")
    @ResponseBody
    public String getorglistpp(String username,String orgCode){

        OutView outView = new OutView();

        try {
            List<YcOrganizationEntity> ycOrganizationEntities = userManagementService.getorglist(orgCode);
            outView.setState(0);
            outView.setMsg(ycOrganizationEntities);
        }catch (Exception e){
            outView.setState(1);
            outView.setMsg("获取组织机构列表失败");
            e.printStackTrace();
        }
        return gson.toJson(outView);
    }

    /**
     * 添加组织机构
     * @return
     */
    @RequestMapping("/addorganization")
    @ResponseBody
    public String addOrganization(OrganizationInput organizationInput){

        OutView outView = new OutView();
        String rs = null;
        try {
            rs = userManagementService.addOrganization(organizationInput);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
        }

        return gson.toJson(outView);
    }


    /**
     * 删除组织机构
     * @param OrgNum
     * @param userOrgNum
     * @return
     */
    @RequestMapping("/deleteorg")
    @ResponseBody
    public String deleteOrg(String OrgNum,String userOrgNum){

        OutView outView = new OutView();
        if(OrgNum.length()==userOrgNum.length()){
            outView.setState(0);
            outView.setMsg("操作失败");
            return gson.toJson(outView);
        }
        String rs = "";
        try {
            rs = userManagementService.deleteOrg(OrgNum+"%");
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(0);
            outView.setMsg(rs);
            e.printStackTrace();
        }
        return gson.toJson(outView);
    }

    /**
     * 添加对接平台
     * @return
     */
    @RequestMapping("/addtoplatform")
    @ResponseBody
    public String addtoplatform(YcPlatformInfoEntity ycPlatformInfoEntity){

        OutView outView = new OutView();
        String rs = null;
        try {
            rs = userManagementService.addtoplatform(ycPlatformInfoEntity);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
        }

        return gson.toJson(outView);
    }


    /**
     * 删除对接平台
     * @return
     */
    @RequestMapping("/deletetoplatform")
    @ResponseBody
    public String deletetoplatform(String platform){

        OutView outView = new OutView();
        String rs = null;
        try {
            rs = userManagementService.deletetoplatform(platform);
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
        }

        return gson.toJson(outView);
    }


    /**
     * 获取对接列表
     * @return
     */
    @RequestMapping("/gettoplatformlist")
    @ResponseBody
    public String gettoplatformlist(){

        OutView outView = new OutView();
        List<YcPlatformInfoEntity> rs = null;
        try {
            rs = userManagementService.gettoplatformlist();
            outView.setState(0);
            outView.setMsg(rs);
        } catch (Exception e) {
            outView.setState(1);
            outView.setMsg("出现异常");
        }

        return gson.toJson(outView);
    }

}
