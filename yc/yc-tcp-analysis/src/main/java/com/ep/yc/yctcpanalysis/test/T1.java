package com.ep.yc.yctcpanalysis.test;

import java.util.HashMap;

/**
 * @Author WQY
 * @Date 2019/10/18 11:25
 * @Version 1.0
 */
public class T1 {


    public static void main(String[] args){

        String msg = "##0317QN=20191018104952418;ST=22;CN=2011;PW=123456;MN=ZX00001;Flag=5;CP=&&DataTime=20191018104900;a34002-Rtd=30,a34002-Flag=N;a34004-Rtd=25,a34004-Flag=N;a01001-Rtd=13.5,a01001-Flag=N;a01002-Rtd=43.8,a01002-Flag=N;a01008-Rtd=30,a01008-Flag=N;a01007-Rtd=3.5,a01007-Flag=N;a34001-Rtd=132,a34001-Flag=N;LA-Rtd=66.8,LA-Flag=N&&AF01";

        String[] msgs = msg.split(";");

        HashMap<String,String> rsMap = new HashMap<String, String>();
        for (int i = 0,num=msgs.length;i<num;i++){
            if(msgs[i].contains(",")){
                String[] params = msgs[i].split(",");
                String param = params[0];
                String[] rss = param.split("=");
                rsMap.put(rss[0],rss[1]);
            }else{
                String[] rss = msgs[i].split("=");
                rsMap.put(rss[0],rss[1]);
            }
        }


        System.out.println(rsMap);

    }
}
