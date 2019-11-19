package com.ep.yc.ycview.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

/**
 * @author WQY
 * @date 2019/9/11 8:11
 */
public class t1 {

    public static void main(String[] args){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        System.out.println("yc_his"+format.format(new Date()));

        String g = "1";

        String[] gh = g.split(",");

        System.out.println(gh);
    }

}
