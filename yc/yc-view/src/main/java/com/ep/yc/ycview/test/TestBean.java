package com.ep.yc.ycview.test;

import com.ep.yc.ycview.requestdb.SendDbBase;
import com.ep.yc.ycview.utils.SpringUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author WQY
 * @date 2019/9/12 16:10
 */
public class TestBean {

    @Autowired
    private SendDbBase sendDbBase = SpringUtils.getApplicationContext().getBean(SendDbBase.class);


    public void pol(){
        System.out.println(new Gson().toJson(sendDbBase.getOrganizationList()));
        System.out.println(sendDbBase);
        System.out.println("123");
    }
}
