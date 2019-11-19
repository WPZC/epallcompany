package com.ep.yc.ycview.service.impl;

import com.ep.yc.ycview.entity.*;
import com.ep.yc.ycview.requestdb.SendDbBase;
import com.ep.yc.ycview.requestdb.SendTcpServerCompany;
import com.ep.yc.ycview.service.DataMangementService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author WQY
 * @date 2019/9/18 18:36
 */
@Service
public class DataMangementServiceImpl implements DataMangementService {

    @Autowired
    private SendDbBase sendDbBase;

    @Autowired
    private SendTcpServerCompany sendTcpServerCompany;

    private Gson gson = new Gson();

    @Override
    public List<Object> getdeviceinfolist(String orgCode) {

        String json = sendDbBase.getdeviceinfolist(orgCode);
        if(json==null||json.equals("")){
            return null;
        }

        List<YcDeviceInfoRealtimeEntity> ycs = new Gson().fromJson(json, new TypeToken<List<YcDeviceInfoRealtimeEntity>>() {
        }.getType());



        for (int i = 0; i < ycs.size(); i++) {
            double fs = Double.parseDouble(ycs.get(i).getWindDirection());
            if (fs <= 22 || (337 <= fs && fs <= 360)) {// D7<22或者 337≤D7≤360为正北风
                ycs.get(i).setWindDirection("正北风");
            } else if (22 <= fs && fs < 67) { // 22≤D7＜67 风向为东北风
                ycs.get(i).setWindDirection("东北风");
            } else if (67 <= fs && fs < 112) { // 67≤D7＜112 风向为正东风
                ycs.get(i).setWindDirection("正东风");
            }else if (112 <= fs && fs < 157) { // 112≤D7＜157 风向为东南风
                ycs.get(i).setWindDirection("东南风");
            }else if (157 <= fs && fs < 202) { // 157≤D7＜202 风向为正南风
                ycs.get(i).setWindDirection("正南风");
            }else if (202 <= fs && fs < 247) { // 202≤D7＜247 风向为西南风
                ycs.get(i).setWindDirection("西南风");
            }else if (247 <= fs && fs < 292) { // 247≤D7＜292 风向为正西风
                ycs.get(i).setWindDirection("正西风");
            }else if (292 <= fs && fs < 337) { // 292≤D7＜337 风向为西北风
                ycs.get(i).setWindDirection("西北风");
            }
        }

        List<Object> rs = new ArrayList<>();
        List<String> zrs;
        for (int i = 0, num = ycs.size(); i < num; i++) {
            zrs = new ArrayList<>();
            zrs.add(ycs.get(i).getLongitude());
            zrs.add(ycs.get(i).getLatitude());

            String table = "<table border='0' width='350px' cellspacing='' cellpadding=''>\n" +
                    "<tr style='background: #EEEEEE;'>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;' style='border:1px solid #CCCCCC; text-align: center;' colspan='4' style='border:1px solid #CCCCCC; text-align: center;'>\n" +
                    "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>" + ycs.get(i).getOrganizationName() + "</h4>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center; line-height: 30px;' style='border:1px solid #CCCCCC; text-align: center;'>PM2.5:</td>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;' style='border:1px solid #CCCCCC; text-align: center;'><span style='color:#0099ff;'>" + ycs.get(i).getPm25() + "</span></td>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;' style='border:1px solid #CCCCCC; text-align: center;'>PM10:</td>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;'><span style='color:#0099ff;'>" + ycs.get(i).getPm10() + "</span></td>\n" +
                    "</tr>\n" +
                    "<tr style='background: #EEEEEE;'>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;' style='border:1px solid #CCCCCC; text-align: center;'>噪音:</td>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;'>"+ycs.get(i).getNoise()+"</td>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;' style='border:1px solid #CCCCCC; text-align: center;'>空气温度:</td>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;'>"+ycs.get(i).getAirTemperature()+"</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;' style='border:1px solid #CCCCCC; text-align: center;'>风速:</td>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;'>"+ycs.get(i).getWindSpeed()+"</td>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;' style='border:1px solid #CCCCCC; text-align: center;'> 空气湿度:</td>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;'>"+ycs.get(i).getAirHumidity()+"</td>\n" +
                    "</tr>\n" +
                    "<tr style='background: #EEEEEE;'>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;' style='border:1px solid #CCCCCC; text-align: center;'>风向:</td>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;' style='border:1px solid #CCCCCC; text-align: center;' colspan='3'>"+ycs.get(i).getWindDirection()+"</td>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;' style='border:1px solid #CCCCCC; text-align: center;'>时间:</td>\n" +
                    "<td style='border:1px solid #CCCCCC; text-align: center;' style='border:1px solid #CCCCCC; text-align: center;' colspan='3'>"+ycs.get(i).getUpdatetime()+"</td>\n" +
                    "</tr>\n" +
                    "</table>\n" +
                    "";
            //"<table style=\"background: #EEEEEE;\" border='1' width='100%' cellspacing='' cellpadding=''><tr><td colspan=\"4\"><h4 style=\"margin:0 0 5px 0;padding:0.2em 0\">" + ycs.get(i).getOrganizationName() + "</h4></td> </tr> <tr><td>PM2.5:</td><td><span style=\"color:#009900;\">" + ycs.get(i).getPm25() + "</span></td><td>PM10:</td><td><span style=\"color:#009900;\">" + ycs.get(i).getPm10() + "</span></td> </tr> <tr><td>噪音:</td><td>"+ycs.get(i).getNoise()+"</td><td>空气温度:</td><td>"+ycs.get(i).getAirTemperature()+"</td> </tr> <tr><td>风速:</td><td>"+ycs.get(i).getWindSpeed()+"</td><td> 空气湿度:</td><td>"+ycs.get(i).getAirHumidity()+"</td> </tr> <tr><td>风向:</td><td colspan=\"3\">"+ycs.get(i).getWindDirection()+"</td> </tr> <tr><td>时间:</td><td colspan=\"3\">"+ycs.get(i).getUpdatetime()+"</td> </tr></table>";
            zrs.add(table);
            zrs.add(ycs.get(i).getOrganizationName());
            rs.add(zrs);
        }

        return rs;
    }

    @Override
    public YcRealtimeinfoEntity getavgrealtimeinfo(String orgNum) {

        String json = sendDbBase.getavgrealtimeinfo(orgNum);

        if(json==null||json.equals("{}")||json.equals("")){
            return null;
        }

        YcRealtimeinfoEntity rs = new Gson().fromJson(json, YcRealtimeinfoEntity.class);
        double fs = Double.parseDouble(rs.getWindDirection());
        if (fs <= 22 || (337 <= fs && fs <= 360)) {// D7<22或者 337≤D7≤360为正北风
            rs.setWindDirection("正北风");
        } else if (22 <= fs && fs < 67) { // 22≤D7＜67 风向为东北风
            rs.setWindDirection("东北风");
        } else if (67 <= fs && fs < 112) { // 67≤D7＜112 风向为正东风
            rs.setWindDirection("正东风");
        }else if (112 <= fs && fs < 157) { // 112≤D7＜157 风向为东南风
            rs.setWindDirection("东南风");
        }else if (157 <= fs && fs < 202) { // 157≤D7＜202 风向为正南风
            rs.setWindDirection("正南风");
        }else if (202 <= fs && fs < 247) { // 202≤D7＜247 风向为西南风
            rs.setWindDirection("西南风");
        }else if (247 <= fs && fs < 292) { // 247≤D7＜292 风向为正西风
            rs.setWindDirection("正西风");
        }else if (292 <= fs && fs < 337) { // 292≤D7＜337 风向为西北风
            rs.setWindDirection("西北风");
        }

        return rs;
    }

    @Override
    public List<Object> findByDayMsg(String orgNum) {
        String json = sendDbBase.findByDayMsg(orgNum);
        if(json==null||json.equals("")){
            return null;
        }
        List<IconEntity> rs = new Gson().fromJson(json, new TypeToken<List<IconEntity>>() {
        }.getType());
        List<String> pm25list = new ArrayList<String>();
        List<String> pm10list = new ArrayList<String>();
        for (int i = 0, num = rs.size(); i < num; i++) {
            pm25list.add(rs.get(i).getPm25());
            pm10list.add(rs.get(i).getPm10());
        }
        List<Object> outRs = new ArrayList<Object>();
        outRs.add(pm25list);
        outRs.add(pm10list);
        return outRs;
    }

    @Override
    public List<Object> findAllByLs(String startTime, String endTime, String sb) {
        String json = sendDbBase.findAllByLs(startTime, endTime, sb);
        System.out.println(json);
        List<YcRealtimeinfoEntity> rs = new Gson().fromJson(json, new TypeToken<List<YcRealtimeinfoEntity>>() {
        }.getType());
        List<Object> outRs = new ArrayList<Object>();
        outRs.add(rs);
        return outRs;
    }

    @Override
    public List<Object> getrealtimeinfo(String orgNum) {

        String json = sendDbBase.getrealtimeinfo(orgNum);
        if(json==null||json.equals("")){
            return null;
        }
        List<YcDeviceInfoRealtimeEntity> rs = new Gson().fromJson(json, new TypeToken<List<YcDeviceInfoRealtimeEntity>>() {
        }.getType());
        for (int i = 0; i < rs.size(); i++) {
            double fs = Double.parseDouble(rs.get(i).getWindDirection());
            rs.get(i).setWindSpeed(rs.get(i).getWindSpeed()/10);
            if (fs <= 22 || (337 <= fs && fs <= 360)) {// D7<22或者 337≤D7≤360为正北风
                rs.get(i).setWindDirection("正北风");
            } else if (22 <= fs && fs < 67) { // 22≤D7＜67 风向为东北风
                rs.get(i).setWindDirection("东北风");
            } else if (67 <= fs && fs < 112) { // 67≤D7＜112 风向为正东风
                rs.get(i).setWindDirection("正东风");
            }else if (112 <= fs && fs < 157) { // 112≤D7＜157 风向为东南风
                rs.get(i).setWindDirection("东南风");
            }else if (157 <= fs && fs < 202) { // 157≤D7＜202 风向为正南风
                rs.get(i).setWindDirection("正南风");
            }else if (202 <= fs && fs < 247) { // 202≤D7＜247 风向为西南风
                rs.get(i).setWindDirection("西南风");
            }else if (247 <= fs && fs < 292) { // 247≤D7＜292 风向为正西风
                rs.get(i).setWindDirection("正西风");
            }else if (292 <= fs && fs < 337) { // 292≤D7＜337 风向为西北风
                rs.get(i).setWindDirection("西北风");
            }
        }
        List<Object> outRs = new ArrayList<Object>();
        outRs.add(rs);
        return outRs;
    }

    @Override
    public List<YcAlarmInfoEntity> getAlarminfoList(String orgNum,String startTime,String endTime,String deviceNum) {

        List<YcAlarmInfoEntity> rs;
        if(startTime==null||startTime.equals("")||endTime==null||endTime.equals("")||deviceNum==null||deviceNum.equals("")){

            String json = sendDbBase.getAlarminfoList(orgNum);
            rs = new Gson().fromJson(json, new TypeToken<List<YcAlarmInfoEntity>>() {
            }.getType());

        }else{

            String json = sendDbBase.findByOrgNumAndStartTimeAndEndTimeList(orgNum,startTime,endTime,deviceNum);
            rs = new Gson().fromJson(json, new TypeToken<List<YcAlarmInfoEntity>>() {
            }.getType());

        }


        return rs;
    }

    @Value("${fileurl.pic}")
    private String picurl;

    @Override
    public String updateById(long id, String msg, MultipartFile file) {

        System.out.println(picurl);
        String fileName = "";
        if (file.isEmpty()) {
            System.out.println("文件为空空");
        }else{
            fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = picurl; // 上传后的路径
            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return sendDbBase.updateById(id,msg,fileName);
    }

    @Override
    public String[] getVideoUrl(String orgCode) {

        String json = sendDbBase.getdevicelist(orgCode);
        List<YcDeviceInfoEntity> list = gson.fromJson(json,new TypeToken<List<YcDeviceInfoEntity>>(){}.getType());

        for (int i = 0,num = list.size();i<num;i++){
            if(!(list.get(i).getVideoCode().equals("")||list.get(i).getVideoCode()==null)){
                if(list.get(i).getVideoCode().contains(",")){

                    String[] strings = list.get(i).getVideoCode().split(",");
                    return strings;

                }else{
                    String[] strings = new String[1];
                    strings[0] = list.get(i).getVideoCode();
                    return strings;
                }

            }
        }

        return null;
    }

    @Override
    public List<YcAgreementEntity> getAgreementTypes() {

        String json = sendDbBase.getAgreementTypes();
        List<YcAgreementEntity> list = gson.fromJson(json,new TypeToken<List<YcAgreementEntity>>(){}.getType());

        return list;
    }

    @Override
    public List<YcRealtimeinfoEntity> getRealTimeInfoD(String sbid) {

        String json = sendDbBase.getRealTimeInfoD(sbid);

        YcRealtimeinfoEntity rs = gson.fromJson(json,YcRealtimeinfoEntity.class);

        List<YcRealtimeinfoEntity> list = new ArrayList<YcRealtimeinfoEntity>();

        list.add(rs);
        return list;
    }

    @Override
    public String setParams(String msg, String sbid) {
        return sendTcpServerCompany.setParams(msg,sbid);
    }


}
