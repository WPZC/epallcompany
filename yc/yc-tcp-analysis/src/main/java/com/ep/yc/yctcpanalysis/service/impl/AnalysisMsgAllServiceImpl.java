package com.ep.yc.yctcpanalysis.service.impl;

import com.ep.yc.yctcpanalysis.cache.ContextDevicePlatform;
import com.ep.yc.yctcpanalysis.entity.*;
import com.ep.yc.yctcpanalysis.requestApi.DbUtil;
import com.ep.yc.yctcpanalysis.requestApi.SendToClient;
import com.ep.yc.yctcpanalysis.service.AnalysisMsgAll;
import com.ep.yc.yctcpanalysis.service.impl.forward.Send212;
import com.ep.yc.yctcpanalysis.service.impl.forward.SendHeNanAnYang;
import com.ep.yc.yctcpanalysis.service.impl.forward.SendShanDong;
import com.ep.yc.yctcpanalysis.service.impl.forward.SendXingTai;
import com.ep.yc.yctcpanalysis.util.HexByte;
import com.ep.yc.yctcpanalysis.util.Util;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author WQY
 * @Date 2019/10/9 16:26
 * @Version 1.0
 */
@Service
public class AnalysisMsgAllServiceImpl implements AnalysisMsgAll {

    private final Logger logger = LoggerFactory.getLogger(AnalysisMsgAllServiceImpl.class);

    @Autowired
    private DbUtil dbUtil;

    private Gson gson = new Gson();

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String acceptedAnalysisMsg(String message) {

        if(!message.substring(0,2).equals("7E")){
            message = HexByte.characterAi(message,"HEXTOSTR");
        }
        String topOneStr = message.substring(0,1);

        switch (topOneStr){
            case "*"://老协议
                agreementOld(message);
                break;
            case "#"://212协议
                agreement212(message);
                break;
            case "7"://自定义新协议
                agreementCd(message);
                break;
        }


        return null;
    }

    /**
     * 自定义协议
     * @param message
     */
    private void agreementCd(String message) {
        logger.info("自定义协议解析数据:"+message);
        String sbid = message.substring(6,10);
        //判断设备是否为null
        if(ContextDevicePlatform.devicePlatformEntityHashMap.get(sbid)==null){
            logger.info("设备编号未缓存或未录入："+sbid);
            return;
        }
        String body = message.substring(14,message.length()-4);

        //参数总数
        int paramsAll = Integer.parseInt(body.substring(0,2),16);
        //更新时间
        //String upTime = body.substring(2,14);

        String params = body.substring(14);
        YcRealtimeinfoEntity real = new YcRealtimeinfoEntity();
        real.setYdiDeviceNum(sbid);
        real.setYdiDeviceName(ContextDevicePlatform.devicePlatformEntityHashMap.get(sbid).getDeviceName());
        real.setYdiDeviceMn(ContextDevicePlatform.devicePlatformEntityHashMap.get(sbid).getDeviceMn());
        real.setYdiBelongToEnterprise(ContextDevicePlatform.devicePlatformEntityHashMap.get(sbid).getOrganizationName());
        real.setLatitude(ContextDevicePlatform.devicePlatformEntityHashMap.get(sbid).getLatitude());
        real.setLongitude(ContextDevicePlatform.devicePlatformEntityHashMap.get(sbid).getLongitude());
        real.setUpdatetime(new Date());
        for (int i = 0;i<paramsAll;i++){
            //参数ID
            String paramId = params.substring(0,2);
            int paramLength = Integer.parseInt(params.substring(2,4));
            String paramValue = params.substring(4,4+paramLength*2);
            real = paramAnalysis(paramId,params.substring(2,4),paramValue,real);
            params = params.substring(4+paramLength*2);
        }
        //存入数据库
        saveRealTimeAndHisInfo(real,sbid);
    }

    /**
     * 7E协议参数解析
     * @param paramId
     * @param paramValue
     */
    private YcRealtimeinfoEntity paramAnalysis(String paramId,String paramLtngth,String paramValue,YcRealtimeinfoEntity real) {

        switch (paramId) {
            case "01":
                //TSP(光照)
                real.setTsp(ByteConversion(paramLtngth, paramValue));
                break;
            case "02":
                //PM10
                real.setPm10(ByteConversion(paramLtngth, paramValue));
                break;
            case "03":
                //PM2.5
                real.setPm25(ByteConversion(paramLtngth, paramValue));
                break;
            case "04":

                //real.setTsp(ByteConversion(paramLtngth,paramValue));
                break;
            case "05":
                //温度，摄氏度
                real.setAirTemperature(ByteConversion(paramLtngth, paramValue));
                break;
            case "06":
                //湿度
                real.setAirHumidity(ByteConversion(paramLtngth, paramValue));
                break;
            case "07":
                //大气压千帕
                real.setAtmosphericPressure(ByteConversion(paramLtngth, paramValue));
                break;
            case "08":
                //风速 米/秒
                real.setWindSpeed(Double.parseDouble(ByteConversion(paramLtngth, paramValue)));
                break;
            case "09":
                //风向
                real.setWindDirection(ByteConversion(paramLtngth, paramValue));
                break;
            case "0A":
                //噪音
                real.setNoise(ByteConversion(paramLtngth, paramValue));
                break;
            default:
                break;
        }
        return real;
    }

    /**
     * 字节转换
     * @param length
     * @param msg
     * @return
     */
    private String ByteConversion(String length,String msg){

        switch (length){
            case "02"://WORD
                BigInteger bi = new BigInteger(msg, 16);
                return bi.intValue()+"";
            case "04"://FLOAT
                BigInteger l = new BigInteger(msg,16);
                return Float.intBitsToFloat(l.intValue())+"";
            default:
                return null;
        }
    }

    public void saveRealTimeAndHisInfo(YcRealtimeinfoEntity real,String sbid){
        try {
            // 保存到实时数据表中
            dbUtil.save_info(real);
            //判断是否为报警数据
            //判断当前PM10是否大于80
            if(Double.parseDouble(real.getPm10())>200){
                String json = dbUtil.findByDayMsgA(sbid);
                IconEntity iconEntity = new Gson().fromJson(json,IconEntity.class);
                if(Double.parseDouble(iconEntity.getPm10())>200){
                    YcAlarmInfoEntity ala = new YcAlarmInfoEntity();
                    ala.setAlarmType("PM10");
                    ala.setAlarmContent(real.getYdiBelongToEnterprise()+"在"+format.format(real.getUpdatetime())+"PM10的平均值"+real.getPm10()+"超出的设定最大值80");
                    ala.setAlarmCycle("");
                    ala.setAlarmThresholdValue("80");
                    ala.setAlarmTime(real.getUpdatetime());
                    ala.setAlarmTitle("PM10超标");
                    ala.setDeviceName(real.getYdiDeviceNum());
                    ala.setEnterpriseName(real.getYdiBelongToEnterprise());
                    ala.setIsProcessing(0L);
                    dbUtil.saveAlarmInfo(ala);
                }
            }
            //212
            //分析对接平台转为对应数据
            analysisPlatform(real);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 212协议
     * @param message
     */
    public void agreement212(String message){

        String[] msgs = message.split(";");

        HashMap<String,String> rsMap = new HashMap<String, String>();
        for (int i = 0,num=msgs.length;i<num;i++){
            if(msgs[i].contains(",")){
                String[] params = msgs[i].split(",");
                String param = params[0];
                String[] rss = param.split("=");
                rsMap.put(rss[0].toUpperCase(),rss[1]);
            }else{
                String[] rss = msgs[i].split("=");
                rsMap.put(rss[0].toUpperCase(),rss[1]);
            }
        }

        YcDeviceInfoEntity ycDeviceInfoEntity = new YcDeviceInfoEntity();
        try {
            ycDeviceInfoEntity = dbUtil.findByDeviceNum(rsMap.get("MN"));
            if(ycDeviceInfoEntity==null){
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(rsMap.get("LA-RTD"));
        YcRealtimeinfoEntity real = new YcRealtimeinfoEntity();
        real.setYdiDeviceNum(ycDeviceInfoEntity.getDeviceNum());// 设备MN
        real.setYdiDeviceName(ycDeviceInfoEntity.getDeviceName());// 设备名称
        real.setYdiBelongToEnterprise(ycDeviceInfoEntity.getOrganizationName());// 所属企业
        real.setPm25(rsMap.get("A34004-RTD")==null?"":rsMap.get("A34004-RTD"));// pm2.5
        real.setPm10(rsMap.get("A34002-RTD")==null?"":rsMap.get("A34002-RTD"));// pm10
        real.setNoise(rsMap.get("LA-RTD")==null?"":rsMap.get("LA-RTD"));// 噪音
        real.setAirTemperature(rsMap.get("A01001-RTD")==null?"":rsMap.get("A01001-RTD"));// 空气温度
        real.setAirHumidity(rsMap.get("A01002-RTD")==null?"":rsMap.get("A01002-RTD"));// 空气湿度
        real.setWindSpeed(Double.parseDouble(rsMap.get("A01007-RTD")==null?"0":rsMap.get("A01007-RTD")));// 风速
        real.setWindDirection(rsMap.get("A01008-RTD")==null?"":rsMap.get("A01008-RTD"));// 风向
        real.setTsp(rsMap.get("A34001-RTD")==null?"":rsMap.get("A34001-RTD"));// 光照强度（tsp）
        real.setOxygenFactor("");// 富氧因子
        real.setAtmosphericPressure(rsMap.get("A01006-RTD")==null?"":rsMap.get("A01006-RTD"));// 大气压力
        real.setUpdatetime(new Date());// 更新时间
        real.setYdiDeviceMn(ycDeviceInfoEntity.getDeviceMn());

        saveRealTimeAndHisInfo(real,ycDeviceInfoEntity.getDeviceNum());
    }

    /**
     * 老协议
     * @param message
     */
    public void agreementOld(String message){
        // 解析 从$处分割，需要加上\\前缀
        String key = message.split("\\$")[1];
        YcDeviceInfoEntity ycDeviceInfoEntity = new YcDeviceInfoEntity();
        try {
            ycDeviceInfoEntity = dbUtil.findByDeviceNum(key);
            if(ycDeviceInfoEntity==null){
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (message.substring(1, 2).equals("1")) {
            YcRealtimeinfoEntity real = new YcRealtimeinfoEntity();
            String msgs[] = message.split("\\$");
            real.setYdiDeviceNum(key);// 设备编号
            real.setYdiDeviceName(ycDeviceInfoEntity.getDeviceName());// 设备名称
            real.setYdiBelongToEnterprise(ycDeviceInfoEntity.getOrganizationName());// 所属企业
            real.setPm25(String.valueOf(msgs[3]));// pm2.5
            real.setPm10(String.valueOf(msgs[5]));// pm10
            real.setNoise(String.valueOf(Double.parseDouble(msgs[7]) / 10));// 噪音
            real.setAirTemperature(String.valueOf(Double.parseDouble(msgs[9]) / 10));// 空气温度
            real.setAirHumidity(String.valueOf(Double.parseDouble(msgs[11]) / 10));// 空气湿度
            real.setWindSpeed(Double.parseDouble(msgs[13]));// 风速
            real.setWindDirection(String.valueOf(Double.parseDouble(msgs[15])));// 风向
            real.setTsp(String.valueOf(Double.parseDouble(msgs[17])));// 光照强度（tsp）
            real.setOxygenFactor("");// 富氧因子
            real.setAtmosphericPressure(String.valueOf(Double.parseDouble(msgs[19])));// 大气压力
            real.setUpdatetime(new Date());// 更新时间
            real.setYdiDeviceMn(ycDeviceInfoEntity.getDeviceMn());

            try {
                // 保存到实时数据表中
                dbUtil.save_info(real);
                //判断是否为报警数据
                //判断当前PM10是否大于80
                if(Double.parseDouble(real.getPm10())>200){
                    String json = dbUtil.findByDayMsgA(key);
                    IconEntity iconEntity = new Gson().fromJson(json,IconEntity.class);
                    if(Double.parseDouble(iconEntity.getPm10())>200){
                        YcAlarmInfoEntity ala = new YcAlarmInfoEntity();
                        ala.setAlarmType("PM10");
                        ala.setAlarmContent(real.getYdiBelongToEnterprise()+"在"+format.format(real.getUpdatetime())+"PM10的平均值"+real.getPm10()+"超出的设定最大值200");
                        ala.setAlarmCycle("");
                        ala.setAlarmThresholdValue("200");
                        ala.setAlarmTime(real.getUpdatetime());
                        ala.setAlarmTitle("PM10超标");
                        ala.setDeviceName(real.getYdiDeviceNum());
                        ala.setEnterpriseName(real.getYdiBelongToEnterprise());
                        ala.setIsProcessing(0L);
                        dbUtil.saveAlarmInfo(ala);
                    }
                }
                //old
                //分析对接平台转为对应数据
                analysisPlatform(real);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public void analysisPlatform212(YcRealtimeinfoEntity real){
//
//        //根据设备编码从缓存中取值
//        if(ContextDevicePlatform.devicePlatformEntityHashMapMn.get(real.getYdiDeviceNum())!=null){
//            String[] ips = ContextDevicePlatform.devicePlatformEntityHashMapMn.get(real.getYdiDeviceNum()).getToIp().split(",");
//            String[] ports = ContextDevicePlatform.devicePlatformEntityHashMapMn.get(real.getYdiDeviceNum()).getToPort().split(",");
//            String[] agreements = ContextDevicePlatform.devicePlatformEntityHashMapMn.get(real.getYdiDeviceNum()).getAgreementType().split(",");
//            String[] pws = null;
//            if(ContextDevicePlatform.devicePlatformEntityHashMapMn.get(real.getYdiDeviceNum()).getPw()!=null){
//                pws = ContextDevicePlatform.devicePlatformEntityHashMapMn.get(real.getYdiDeviceNum()).getPw().split(",");
//            }
//            for (int i = 0,num = agreements.length;i<num;i++){
//                System.out.println(ips[i]+":"+ports[i]+"--"+pws[i]);
//                switch (agreements[i]){
//                    case "212":
//                        send212(real,ips[i],ports[i],pws[i]);
//                        break;
//                }
//            }
//        }
//
//
//    }


    public void analysisPlatform(YcRealtimeinfoEntity real){

        //根据设备编码从缓存中取值
        if(ContextDevicePlatform.devicePlatformEntityHashMap.get(real.getYdiDeviceNum())!=null){
            String[] ips = ContextDevicePlatform.devicePlatformEntityHashMap.get(real.getYdiDeviceNum()).getToIp().split(",");
            String[] ports = ContextDevicePlatform.devicePlatformEntityHashMap.get(real.getYdiDeviceNum()).getToPort().split(",");
            String[] agreements = ContextDevicePlatform.devicePlatformEntityHashMap.get(real.getYdiDeviceNum()).getAgreementType().split(",");
            String[] pws = null;
            if(ContextDevicePlatform.devicePlatformEntityHashMap.get(real.getYdiDeviceNum()).getPw()!=null){
                pws = ContextDevicePlatform.devicePlatformEntityHashMap.get(real.getYdiDeviceNum()).getPw().split(",");
            }
            for (int i = 0,num = agreements.length;i<num;i++){
                System.out.println(ips[i]+":"+ports[i]+"--"+pws[i]);
                switch (agreements[i]){
                    case "基础212协议":
                        new Send212("任县",ips[i],ports[i]).editMsg(real,pws[i]);
                        break;
                    case "山东自定协议":
                        new SendShanDong("山东",ips[i],ports[i]).editMsg(real,pws[i]);
                        break;
                    case "邢台自定协议":
                        new SendXingTai("邢台",ips[i],ports[i]).editMsg(real,pws[i]);
                        break;
                    case "衡水冀州市自定协议":
                        //传输不明确
                        break;
                    case "河南安阳自定协议":
                        new SendHeNanAnYang("河南安阳",ips[i],ports[i]).editMsg(real,pws[i]);
                        break;
                }
            }
        }


    }

    /**
     * 按文安协议进行解析
     * @param real
     * @return
     */
    private void sendWenAn(YcRealtimeinfoEntity real, String ip, String port, String pw) {

//        if(real.get){
//
//        }

    }
}
