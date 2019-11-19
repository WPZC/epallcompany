package com.ep.yc.yctcpgatewayserver.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Inspection {

    /**
     * 两个字节进行异或
     * @param strHex_X 前一个字节
     * @param strHex_Y 后一个字节
     * @return 异或结果
     */
    public static String xor(String strHex_X,String strHex_Y){
        //将x、y转成二进制形式
        String anotherBinary=Integer.toBinaryString(Integer.valueOf(strHex_X,16));
        String thisBinary=Integer.toBinaryString(Integer.valueOf(strHex_Y,16));
        String result = "";
        //判断是否为8位二进制，否则左补零
        if(anotherBinary.length() != 8){
            for (int i = anotherBinary.length(); i <8; i++) {
                anotherBinary = "0"+anotherBinary;
            }
        }
        if(thisBinary.length() != 8){
            for (int i = thisBinary.length(); i <8; i++) {
                thisBinary = "0"+thisBinary;
            }
        }
        //异或运算
        for(int i=0;i<anotherBinary.length();i++){
            //如果相同位置数相同，则补0，否则补1
            if(thisBinary.charAt(i)==anotherBinary.charAt(i))
                result+="0";
            else{
                result+="1";
            }
        }
        return Integer.toHexString(Integer.parseInt(result, 2));
    }


    /**
     * 输入hex字符串
     * @param hexStr
     * @return 异或校验结果
     */
    public static String getInspection(String hexStr){

        List<String> list = new ArrayList<String>();

        String hex = "";
        for (int i = 0; i < hexStr.length()/2; i++) {
            list.add(hexStr.substring(2*i, 2*i+2));
        }
        for (int i = 0; i < list.size()-1; i++) {
            if(i==0){
                hex = xor(list.get(i),list.get(i+1));
            }else{
                hex = xor(hex,list.get(i+1));
            }
        }

        hex = hex.toUpperCase();
        System.out.println(hex);

        if(hex.length()==1){
            hex = "0"+hex;
        }

        return hex;
    }

    public static int getCount(String str, String tag) {
        int index = 0;
        int count = 0;
        while ((index = str.indexOf(tag)) != -1 ) {
            str = str.substring(index + tag.length());
            count++;
        }
        return count;
    }


    public static List<String> getPackageNum(String body) {

        List<String> rs = new LinkedList<String>();

        StringBuilder stringBuilder = new StringBuilder();

        int count = 0;
        for (int i = 0,legth = body.length()/2;i<legth;i++){
            if(body.substring(2*i,2*i+2).equals("7E")){
                count++;
                stringBuilder.append(body.substring(2*i,2*i+2));
                if(count==2){
                    rs.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                    count = 0;
                }

            }else {
                stringBuilder.append(body.substring(2*i,2*i+2));
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //7E000200010009027E
        String str = "0002FFFE000D55AA55AA";

        System.out.println(getInspection(str));

    }
}
