package com.ep.yc.ycview.service.impl;

import com.ep.yc.ycview.entity.YcDeviceInfoEntity;
import com.ep.yc.ycview.entity.YcDeviceInfoRealtimeEntity;
import com.ep.yc.ycview.entity.YcPlatformInfoEntity;
import com.ep.yc.ycview.requestdb.SendDbBase;
import com.ep.yc.ycview.service.DeviceManagementService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @author WQY
 * @date 2019/9/12 14:46
 */
@Service
public class DeviceManagementServiceImpl implements DeviceManagementService {

    @Autowired
    private SendDbBase sendDbBase;

    private Gson gson = new Gson();

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public String saveDeviceInfo(YcDeviceInfoEntity ycDeviceInfoEntity) {
        return sendDbBase.saveDeviceInfo(ycDeviceInfoEntity);
    }

    @Override
    public String deleteByDeviceNum(String deviceNum) {
        String rs = sendDbBase.deleteByDevicenum(deviceNum);
        return rs;
    }

    @Override
    public List<YcDeviceInfoEntity> getdevicelist(String orgCode) throws ParseException {
        String json = sendDbBase.getdevicelist(orgCode);
        List<YcDeviceInfoEntity> ycDeviceInfoEntities = gson.fromJson(json,new TypeToken<List<YcDeviceInfoEntity>>(){}.getType());

        String jsonReal = sendDbBase.getrealtimeinfo(orgCode);
        List<YcDeviceInfoRealtimeEntity> rs = gson.fromJson(jsonReal, new TypeToken<List<YcDeviceInfoRealtimeEntity>>() {
        }.getType());

        HashMap<String,String> timeMap = new HashMap<String, String>();
        for (int i = 0,num = rs.size();i<num;i++){
            timeMap.put(rs.get(i).getDeviceNum(),rs.get(i).getUpdatetime());
        }

        for (int i = 0,num = ycDeviceInfoEntities.size();i<num;i++){

            if(timeMap.get(ycDeviceInfoEntities.get(i).getDeviceNum())!=null){
               if((System.currentTimeMillis()-format.parse(timeMap.get(ycDeviceInfoEntities.get(i).getDeviceNum())).getTime())<600000){
                   //0在线
                   ycDeviceInfoEntities.get(i).setIsOnLine("0");
               }else{
                   //1不在线
                   ycDeviceInfoEntities.get(i).setIsOnLine("1");
               }
            }

        }
        return ycDeviceInfoEntities;
    }

    @Override
    public YcDeviceInfoEntity findByDeviceNum(String devNum) {
        String rs = sendDbBase.findByDeviceNum(devNum);
        YcDeviceInfoEntity deviceInfoEntity = gson.fromJson(rs,YcDeviceInfoEntity.class);
        return deviceInfoEntity;
    }

    @Override
    public String updateDeviceInfo(YcDeviceInfoEntity ycDeviceInfoEntity) {
        return sendDbBase.updateDeviceInfo(ycDeviceInfoEntity);
    }
}
