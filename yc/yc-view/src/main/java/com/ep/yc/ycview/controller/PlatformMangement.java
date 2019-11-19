package com.ep.yc.ycview.controller;

import com.ep.yc.ycview.entity.OutView;
import com.ep.yc.ycview.entity.YcUserEntity;
import com.ep.yc.ycview.service.ClientMangementService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author WQY
 * @date 2019/9/21 16:06
 */
@Controller
@RequestMapping("/device")
@Api(value = "对接平台管理")
public class PlatformMangement {

    @Autowired
    private ClientMangementService clientMangementService;

    private Gson gson = new Gson();

    /**
     * 启动client
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping("/startClient")
    @ResponseBody
    public String startClient(String ip,Integer port){

        OutView outView = new OutView();

        String rs;
        try {
            rs = clientMangementService.startClient(ip, port);
            outView.setState(0);
            outView.setMsg(rs);
            return gson.toJson(outView);
        }catch (Exception e){
            outView.setState(1);
            outView.setMsg("启动失败");
            return gson.toJson(outView);
        }
    }


    /**
     * 启动client
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping("/shutDownClient")
    @ResponseBody
    public String shutDownClient(String ip,Integer port){

        OutView outView = new OutView();

        String rs;
        try {
            rs = clientMangementService.shutDownClient(ip, port);
            outView.setState(0);
            outView.setMsg(rs);
            return gson.toJson(outView);
        }catch (Exception e){
            outView.setState(1);
            outView.setMsg("关闭失败");
            return gson.toJson(outView);
        }
    }
}
