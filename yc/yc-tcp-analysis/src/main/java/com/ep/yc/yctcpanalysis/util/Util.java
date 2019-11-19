package com.ep.yc.yctcpanalysis.util;

import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/12 10:48
 */
public class Util {

    public static ConcurrentHashMap<String, ChannelHandlerContext> map = new ConcurrentHashMap<String, ChannelHandlerContext>();
    /**
     *
     *<b>Summary: 忽略大小写比较两个字符串</b>
     * ignoreCaseEquals()
     * @param str1
     * @param str2
     * @return
     */
    public static boolean ignoreCaseEquals(String str1,String str2){
        return str1 == null ? str2 == null :str1.equalsIgnoreCase(str2);
    }


    public static int SumStrAscii(String str){
        byte[] bytestr = str.getBytes();
        int sum = 0;
        for(int i=0;i<bytestr.length;i++){
            sum += bytestr[i];
        }
        return sum;
    }

    public static String leftIndex(String str,int length){
        String msg=str;
        for(int i=0;i<length-str.length();i++){
            msg="0"+msg;
        }
        return msg;
    }

    public static void main(String arg[]){
        String rs = "ST=22;CN=2011;QN=201910294112144CST;PW=yczxjc;MN=lx3561;Flag=0;CP=&&DataTime=201910294112144;a34001-Rtd=132,a34001-Flag=N;a34002-Rtd=30,a34002-Flag=N;a34004-Rtd=25,a34004-Flag=N;a01001-Rtd=13.5,a01001-Flag=N;a01002-Rtd=43.8,a01002-Flag=N;a01006-Rtd=0.0,a01006-Flag=N;a01007-Rtd=3.5,a01007-Flag=N;a01008-Rtd=30,a01008-Flag=N;Leq-Rtd=66.8,Leq-Flag=N;&&";
        System.out.println(CRC16(rs));
    }


    /**
     * @return
     * @Description: HJ212污染监测 CRC16校验算法
     * @author *****
     */
    public static String CRC16(String temp) {
        //String temp ="ST=32;CN=2011;PW=122333;MN=88888880000005;CP=&&DataTime=20120416225856;060-Rtd=0.06,060-Flag=N;011-Rtd=0.00,011-Flag=N;001-Rtd=7.64,001-Flag=N&&##0077QN=20120416225111069;ST=91;CN=9021;PW=122333;MN=88888880000006;Flag=1;CP=&&&&4100##0101QN=20160801085857223;ST=32;CN=1062;PW=100000;MN=010000A8900016F000169DC0;Flag=5 ;CP=&&RtdInterval=30&&";
        Integer[] regs = new Integer[temp.length()];
        for (int i = 0; i < temp.length(); i++) {
            regs[i] = (int) temp.charAt(i);
        }

        int por = 0xFFFF;
        for (int j = 0; j < regs.length; j++) {
            por = por >> 8;
            por ^= regs[j];
            for (int i = 0; i < 8; i++) {
                if ((por & 0x01) == 1) {
                    por = por >> 1;
                    por = por ^ 0xa001;
                } else {
                    por = por >> 1;
                }
            }
        }

        return Integer.toHexString(por).toUpperCase();
    }


}
