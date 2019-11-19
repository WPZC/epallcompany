package com.epyc.ycdbbase.db_z;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/16 18:19
 */
public class Utils {

    public static List<String> addTables(String cntDateBeg, String cntDateEnd) {
        //List<String> list = new ArrayList<>();
        List<String> list = new ArrayList<>();
        //拆分成数组
        String[] dateBegs = cntDateBeg.substring(0,10).split("-");
        String[] dateEnds = cntDateEnd.substring(0,10).split("-");
        //开始时间转换成时间戳
        Calendar start = Calendar.getInstance();
        start.set(Integer.valueOf(dateBegs[0]), Integer.valueOf(dateBegs[1]) - 1, Integer.valueOf(dateBegs[2]));
        Long startTIme = start.getTimeInMillis();
        //结束时间转换成时间戳
        Calendar end = Calendar.getInstance();
        end.set(Integer.valueOf(dateEnds[0]), Integer.valueOf(dateEnds[1]) - 1, Integer.valueOf(dateEnds[2]));
        Long endTime = end.getTimeInMillis();
        //定义一个一天的时间戳时长
        Long oneDay = 1000 * 60 * 60 * 24l;
        Long time = startTIme;
        //循环得出
        while (time <= endTime) {
            list.add( "yc_his" + (new SimpleDateFormat("yyyyMMdd").format(new Date(time))) );

            time += oneDay;
        }
        //list = list.substring(0, list.length() - 5);
        return list;
    }


    public static String addDates(String cntDateBeg, String cntDateEnd,String sb) {
        //List<String> list = new ArrayList<>();
        String list = "";
        //拆分成数组
        String[] dateBegs = cntDateBeg.substring(0,10).split("-");
        String[] dateEnds = cntDateEnd.substring(0,10).split("-");
        //开始时间转换成时间戳
        Calendar start = Calendar.getInstance();
        start.set(Integer.valueOf(dateBegs[0]), Integer.valueOf(dateBegs[1]) - 1, Integer.valueOf(dateBegs[2]));
        Long startTIme = start.getTimeInMillis();
        //结束时间转换成时间戳
        Calendar end = Calendar.getInstance();
        end.set(Integer.valueOf(dateEnds[0]), Integer.valueOf(dateEnds[1]) - 1, Integer.valueOf(dateEnds[2]));
        Long endTime = end.getTimeInMillis();
        //定义一个一天的时间戳时长
        Long oneDay = 1000 * 60 * 60 * 24l;
        Long time = startTIme;
        //循环得出
        while (time <= endTime) {
            list += " SELECT * FROM yc_his" + (new SimpleDateFormat("yyyyMMdd").format(new Date(time))) + " WHERE ydi_device_num = '"+sb+"'  UNION";
            time += oneDay;
        }
        list = list.substring(0, list.length() - 5);
        return list;
    }

    public static void main(String args[]){
        String s = "2019-09-16 16:00:37";
        String a = "2019-09-26 16:00:37";
        System.out.println(addTables(s,a));
    }
}
