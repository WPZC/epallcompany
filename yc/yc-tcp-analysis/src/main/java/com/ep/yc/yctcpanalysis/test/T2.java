package com.ep.yc.yctcpanalysis.test;

import com.ep.yc.yctcpanalysis.service.impl.forward.Send212;
import com.ep.yc.yctcpanalysis.service.impl.forward.SendShanDong;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * @Author WQY
 * @Date 2019/10/19 15:17
 * @Version 1.0
 */
public class T2 {

    public static void main(String[] args) throws Exception {
        String str = "JTVCJTdCJTIyTU4lMjIlM0ElMjJ5Y2pjMTUyQyUyMkRREFUQSI6WhdGFUa6Mjg6MDgiLC";

        String msg = "{\"01\":\"192.168.1.27\",\"02\":\"8895\"}";

        HashMap<String,String> map = new HashMap<String, String>();
        Gson gson = new Gson();
        map = gson.fromJson(msg,map.getClass());
        System.out.println(map);

//        Send212 send212 = new Send212("基础212协议","","");
//        SendShanDong sendShanDong = new SendShanDong("山东","","");

//        System.out.println(send212.address);
//        System.out.println(sendShanDong.address);

        System.out.println(Math.round(0.526));
    }
}
