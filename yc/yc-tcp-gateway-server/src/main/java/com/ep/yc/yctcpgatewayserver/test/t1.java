package com.ep.yc.yctcpgatewayserver.test;

import com.ep.yc.yctcpgatewayserver.utils.HexByte;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WQY
 * @date 2019/9/24 10:10
 */
public class t1 {
    public static void main(String[] args){

        System.out.println("0000017E00".indexOf("EE"));

        int r = -4;
        String hex = Integer.toHexString(r);
        BigInteger bi = new BigInteger(hex, 16);
        System.out.println(bi.intValue());

        float f = 4.02f;
        Integer.toHexString(Float.floatToIntBits(f));
        System.out.println(Integer.toHexString(Float.floatToIntBits(f)));
        String hexs = Integer.toHexString(Float.floatToIntBits(f));
        BigInteger l = new BigInteger(hexs,16);
        System.out.println(Float.intBitsToFloat(l.intValue()));


        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        System.out.println(format.format(new Date()));


        String ip = "192.168.1.27";
        System.out.println(HexByte.characterAi(ip,"STRTOHEX"));
        System.out.println(HexByte.characterAi(HexByte.characterAi(ip,"STRTOHEX"),"HEXTOSTR"));
//        Bike9 obj = new Bike9();
//        obj.run();
    }
}
//class Bike9{
//    final Integer speedlimit;
//    void run(){
//        speedlimit+=400;
//        System.out.println(speedlimit);
//    }
//}
