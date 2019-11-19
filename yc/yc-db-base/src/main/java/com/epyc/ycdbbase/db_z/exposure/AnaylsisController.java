package com.epyc.ycdbbase.db_z.exposure;

import com.epyc.ycdbbase.db_z.Utils;
import com.epyc.ycdbbase.db_z.dao.EntityM;
import com.epyc.ycdbbase.db_z.dao.Yc_DeviceInfo_Jap;
import com.epyc.ycdbbase.db_z.dao.Yc_Realtimeinfo_Jap;
import com.epyc.ycdbbase.entity.YcDeviceInfoEntity;
import com.epyc.ycdbbase.entity.YcRealtimeinfoEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WQY
 * @date 2019/9/9 15:30
 */
@RestController
@RequestMapping("/anaylsisToDb")
public class AnaylsisController {

    @Autowired
    private Yc_Realtimeinfo_Jap yc_realtimeinfo_jap;
    @Autowired
    private EntityM entityM;
    @Autowired
    private Yc_DeviceInfo_Jap yc_deviceInfo_jap;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    @PostMapping("/save_info")
    @ResponseBody
    public int save_info(@RequestBody YcRealtimeinfoEntity ycRealtimeinfoEntity) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        String table = "yc_his" + df.format(new Date());
        System.out.println(table);
        try {
            List<YcRealtimeinfoEntity> list = yc_realtimeinfo_jap.findByYdiDeviceNum(ycRealtimeinfoEntity.getYdiDeviceNum());
            if (list.size() > 0) {
                int i = yc_realtimeinfo_jap.update(ycRealtimeinfoEntity.getYdiDeviceName(), ycRealtimeinfoEntity.getYdiBelongToEnterprise(),
                        ycRealtimeinfoEntity.getPm25(), ycRealtimeinfoEntity.getPm10(), ycRealtimeinfoEntity.getNoise(),
                        ycRealtimeinfoEntity.getAirTemperature(), ycRealtimeinfoEntity.getAirHumidity(), ycRealtimeinfoEntity.getWindSpeed(),
                        ycRealtimeinfoEntity.getWindDirection(), ycRealtimeinfoEntity.getTsp(), ycRealtimeinfoEntity.getOxygenFactor(),
                        ycRealtimeinfoEntity.getAtmosphericPressure(), ycRealtimeinfoEntity.getUpdatetime(), ycRealtimeinfoEntity.getYdiDeviceNum(),ycRealtimeinfoEntity.getYdiDeviceMn()
                        ,ycRealtimeinfoEntity.getLongitude(),ycRealtimeinfoEntity.getLatitude());
                System.out.println(i);
            } else {
                yc_realtimeinfo_jap.saveAndFlush(ycRealtimeinfoEntity);
            }
            entityM.save_info_ls(table, ycRealtimeinfoEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }

        return 0;
    }


    @PostMapping("/findAllByLs")
    @ResponseBody
    public String findAllByLs(String startTime, String endTime, String sb) {
        String tabels = Utils.addDates(startTime, endTime,sb);
        List list = entityM.findAllByLs(tabels, startTime, endTime, sb);
        List<YcRealtimeinfoEntity> rsList = new ArrayList<YcRealtimeinfoEntity>();
        for (int i = 0; i < list.size(); i++) {
            YcRealtimeinfoEntity re = new YcRealtimeinfoEntity();
            Object[] obj = (Object[]) list.get(i);
            re.setYdiDeviceNum((String) obj[1]);
            re.setYdiDeviceName((String) obj[2]);
            re.setYdiBelongToEnterprise((String) obj[3]);
            re.setPm25((String) obj[4]);
            re.setPm10((String) obj[5]);
            re.setNoise((String) obj[6]);
            re.setAirTemperature((String) obj[7]);
            re.setAirHumidity((String) obj[8]);
            re.setWindSpeed(((Double) obj[9]) / 10);
            double fs =Double.parseDouble((String) obj[10]);
            if (fs < 22 || (337 < fs && fs < 360)) {// D7<22或者 337≤D7≤360为正北风
                re.setWindDirection("正北风");
            } else if (22 < fs && fs < 67) { // 22≤D7＜67 风向为东北风
                re.setWindDirection("东北风");
            } else if (67 < fs && fs < 112) { // 67≤D7＜112 风向为正东风
                re.setWindDirection("正东风");
            }else if (112 < fs && fs < 157) { // 112≤D7＜157 风向为东南风
                re.setWindDirection("东南风");
            }else if (157 < fs && fs < 202) { // 157≤D7＜202 风向为正南风
                re.setWindDirection("正南风");
            }else if (202 < fs && fs < 247) { // 202≤D7＜247 风向为西南风
                re.setWindDirection("西南风");
            }else if (247 < fs && fs < 292) { // 247≤D7＜292 风向为正西风
                re.setWindDirection("正西风");
            }else if (292 < fs && fs < 337) { // 292≤D7＜337 风向为西北风
                re.setWindDirection("西北风");
            }
            re.setTsp((String) obj[11]);
            re.setOxygenFactor((String) obj[12]);
            re.setAtmosphericPressure((String) obj[13]);
            re.setUpdatetime((Date) obj[14]);
            rsList.add(re);
        }
        return gson.toJson(rsList);
    }


    @PostMapping("/findByDeviceNum")
    @ResponseBody
    public YcDeviceInfoEntity findByDeviceNum(String deviceNum) {
        YcDeviceInfoEntity ycDeviceInfoEntity = yc_deviceInfo_jap.findByDeviceNum(deviceNum);
        return ycDeviceInfoEntity;
    }

}
