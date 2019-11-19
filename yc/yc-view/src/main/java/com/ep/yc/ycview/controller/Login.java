package com.ep.yc.ycview.controller;

import com.ep.yc.ycview.entity.OutView;
import com.ep.yc.ycview.requestdb.SendDbBase;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author WQY
 * @date 2019/9/9 15:38
 */
@RestController
@Api(value = "登录接口")
public class Login {

    @Autowired
    private SendDbBase sendDbBase;

    @RequestMapping("/login")
    @ResponseBody
    @ApiOperation(value="登录", notes="用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名称",dataType = "String"),
            @ApiImplicitParam(name = "password",value = "用户密码",dataType = "String")
    })
    public String login(String username,
                        String password){

        OutView outView = new OutView();

        outView.setState(0);
        outView.setMsg(sendDbBase.isLogin(username,password));

        return new Gson().toJson(outView);

    }

    @RequestMapping("/testthy")
    public ModelAndView testthy(String username, String password){
        ModelAndView modelAndView = new ModelAndView("test");
        modelAndView.addObject("id",2);
        return modelAndView;

    }

}
