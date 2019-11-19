package com.ep.yc.yctcpanalysis.service;

import com.ep.yc.yctcpanalysis.entity.YcRealtimeinfoEntity;
import com.ep.yc.yctcpanalysis.mq.mqutils.AnalysisMessageReceiver;
import com.ep.yc.yctcpanalysis.requestApi.SendToClient;
import com.ep.yc.yctcpanalysis.util.HexByte;
import com.ep.yc.yctcpanalysis.util.HttpRequest;
import com.ep.yc.yctcpanalysis.util.SpringUtils;
import com.ep.yc.yctcpanalysis.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转发,父类默认为212协议
 * 继承此类需要实现editmsg方法并调用sendMsg发送数据
 * @Author WQY
 * @Date 2019/11/18 15:00
 * @Version 1.0
 */
public abstract class Forwarding {

    public static final Logger log = LoggerFactory.getLogger(Forwarding.class);

    public String address;
    public String ip;
    public String port;

    public Forwarding(String address,String ip,String port){
        this.address = address;
        this.ip = ip;
        this.port = port;
    }

    @Autowired
    public SendToClient sendToClient = SpringUtils.getApplicationContext().getBean(SendToClient.class);
    /**
     * 编辑数据
     * @param real
     * @param pwz
     */
    public void editMsg(YcRealtimeinfoEntity real, String pwz){

        SimpleDateFormat df = new SimpleDateFormat("YYYYMMDDhhmmsszzz");
        SimpleDateFormat df2 = new SimpleDateFormat("YYYYMMDDhhmmss");
        String sendInfo = "##";
        String st = "ST=22;";
        String cn = "CN=2011;";
        String QN = "QN="+df.format(new Date()) + ";";
        String pw = "PW="+pwz+";";
        String mn = "MN=" + real.getYdiDeviceMn() + ";Flag=0;";
        String cp = "CP=&&DataTime=" + df2.format(new Date()) + ";";
        String a3401 = "a34001-Rtd=" + real.getTsp() + ",a34001-Flag=N;";
        String a34002 = "a34002-Rtd=" + Double.parseDouble(real.getPm10())*1000 + ",a34002-Flag=N;";
        String a34004 = "a34004-Rtd=" + Double.parseDouble(real.getPm25())*1000 + ",a34004-Flag=N;";
        String a01001 = "a01001-Rtd=" + real.getAirTemperature() + ",a01001-Flag=N;";
        String a01002 = "a01002-Rtd=" + real.getAirHumidity() + ",a01002-Flag=N;";
        String a01006 = "a01006-Rtd=" + real.getAtmosphericPressure() + ",a01006-Flag=N;";
        String a01007 = "a01007-Rtd=" + real.getWindSpeed() + ",a01007-Flag=N;";
        String a01008 = "a01008-Rtd=" + real.getWindDirection() + ",a01008-Flag=N;";
        String Leq = "Leq-Rtd=" + real.getNoise() + ",Leq-Flag=N;&&";
        String acs = st + cn + QN + pw + mn + cp + a3401 + a34002 + a34004 + a01001 + a01002 + a01006 + a01007 + a01008 + Leq;
        String asc = Util.leftIndex(acs.length() + "", 4);
        String crc = Util.CRC16(acs);
        String msgEnd = "\r\n";
        sendInfo += asc + acs + crc + msgEnd;
        System.out.println(sendInfo);
        String message = sendInfo;
        System.out.println(HexByte.characterAi(message,"STRTOHEX"));

        sendMsg(message,"tcp","");
    }

    /**
     * 发送数据
     * @param msg
     * @param type 请求类型（tcp,http）
     *             当为http时必须传url
     */
    protected void sendMsg(String msg,String type,String url){

        String out = ip+":"+port+"!!!!!!!"+HexByte.characterAi(msg,"STRTOHEX")+"!!!!!!!"+address;
        try {
            if(type.equals("tcp")){
                sendToClient.sendToClient(out);
            }else if(type.equals("http")){
                if(url.equals("")||url==null){
                    return;
                }
                //发送 GET 请求
                HttpRequest.sendGet(url, msg);
            }
            log.info(address+"转发成功："+ip+":"+port+"!!!!!!!"+msg+"!!!!!!!"+address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
