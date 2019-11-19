package com.ep.yc.yctcpanalysis.cache.task;

import com.ep.yc.yctcpanalysis.cache.ContextDevicePlatform;
import com.ep.yc.yctcpanalysis.cache.thread.ThreadPoolContext;
import com.ep.yc.yctcpanalysis.entity.YcDeviceInfoEntity;
import com.ep.yc.yctcpanalysis.entity.YcDevicePlatformEntity;
import com.ep.yc.yctcpanalysis.entity.YcPlatformInfoEntity;
import com.ep.yc.yctcpanalysis.requestApi.DbUtil;
import com.ep.yc.yctcpanalysis.util.SpringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author WQY
 * @Date 2019/10/9 16:12
 * @Version 1.0
 */
public class DevicePlatformTask {


    @Autowired
    private static DbUtil dbUtil = SpringUtils.getApplicationContext().getBean(DbUtil.class);

    private Gson gson = new Gson();

    public void cacheDevicePlatformData(){

        //两秒以后启动
        //60秒循环一次
        ThreadPoolContext.executor.scheduleAtFixedRate(()->{

            try {

                String json = dbUtil.getDeviceList();

                List<YcDeviceInfoEntity> deviceInfoEntities = gson.fromJson(json,new TypeToken<List<YcDeviceInfoEntity>>(){}.getType());

                json = dbUtil.gettoplatformlist();

                List<YcPlatformInfoEntity> platformInfoEntities = gson.fromJson(json,new TypeToken<List<YcPlatformInfoEntity>>(){}.getType());

                //将对接平台存入map
                HashMap<String,YcPlatformInfoEntity> pf = new HashMap<String, YcPlatformInfoEntity>();
                for (int i = 0,num=platformInfoEntities.size();i<num;i++){
                    pf.put(platformInfoEntities.get(i).getToPlatformName(),platformInfoEntities.get(i));
                }

                //将设备中的对接平台名称与对接平台对应
                HashMap<String,YcDevicePlatformEntity> devicePlatformEntityHashMap = new HashMap<String, YcDevicePlatformEntity>();
                HashMap<String,YcDevicePlatformEntity> devicePlatformEntityHashMapMn = new HashMap<String, YcDevicePlatformEntity>();
                for (int i = 0,num = deviceInfoEntities.size();i<num;i++){

                    YcDeviceInfoEntity ycDeviceInfoEntity = deviceInfoEntities.get(i);

                    String[] strings = ycDeviceInfoEntity.getToPlatform().split(",");

                    String ips = "";
                    String ports = "";
                    String agreementType = "";
                    String pws = "";
                    for (int y = 0,ynum = strings.length;y<ynum;y++){
                        try {
                            ips += pf.get(strings[y]).getToIp() + ",";
                            ports += pf.get(strings[y]).getToPort() + ",";
                            pws += pf.get(strings[y]).getPw() + ",";
                            agreementType += pf.get(strings[y]).getAgreementType() + ",";
                        }catch (Exception e){
                            System.out.println("缓存失败："+"对接平台缓存问题");
                        }
                    }

                    YcDevicePlatformEntity platformEntity = new YcDevicePlatformEntity();
                    platformEntity.setAgreementType(agreementType);
                    platformEntity.setDeviceName(ycDeviceInfoEntity.getDeviceName());
                    platformEntity.setOrganizationName(ycDeviceInfoEntity.getOrganizationName());
                    platformEntity.setOrganizationNum(ycDeviceInfoEntity.getOrganizationNum());
                    platformEntity.setToIp(ips);
                    platformEntity.setToPort(ports);
                    platformEntity.setDeviceNum(ycDeviceInfoEntity.getDeviceNum());
                    platformEntity.setPw(pws);
                    platformEntity.setDeviceMn(ycDeviceInfoEntity.getDeviceMn());
                    platformEntity.setLatitude(ycDeviceInfoEntity.getLatitude());
                    platformEntity.setLongitude(ycDeviceInfoEntity.getLongitude());

                    devicePlatformEntityHashMap.put(platformEntity.getDeviceNum(),platformEntity);
                    devicePlatformEntityHashMapMn.put(platformEntity.getDeviceMn(),platformEntity);
                }

                ContextDevicePlatform.devicePlatformEntityHashMap = devicePlatformEntityHashMap;
                ContextDevicePlatform.devicePlatformEntityHashMapMn = devicePlatformEntityHashMapMn;

                System.out.println("缓存设备-对接平台数据成功");

            }catch (Exception e){
                e.printStackTrace();
            }
        },2,60, TimeUnit.SECONDS);
    }

}
