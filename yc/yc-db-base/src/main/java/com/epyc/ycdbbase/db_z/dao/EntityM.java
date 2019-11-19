package com.epyc.ycdbbase.db_z.dao;

import com.epyc.ycdbbase.entity.YcRealtimeinfoEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/16 11:00
 */

@Component
@Transactional
public class EntityM {
    //注入的是实体管理器,执行持久化操作
    @PersistenceContext
    EntityManager entityManager;

    public int save_info_ls(String tableName, YcRealtimeinfoEntity re) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sql = "insert into "+tableName +"(ydi_device_num,ydi_device_name,ydi_belong_to_enterprise,pm25,pm10,noise,air_temperature,air_humidity,wind_speed,wind_direction,tsp,oxygen_factor,atmospheric_pressure,updatetime) values ('"+re.getYdiDeviceNum()+"','"+re.getYdiDeviceName()+"','"+re.getYdiBelongToEnterprise()+"','"+re.getPm25()+"','"+re.getPm10()+"','"+re.getNoise()+"','"+re.getAirTemperature()+"','"+re.getAirHumidity()+"',"+re.getWindSpeed()+",'"+re.getWindDirection()+"','"+re.getTsp()+"','"+re.getOxygenFactor()+"','"+re.getAtmosphericPressure()+"','"+df.format(re.getUpdatetime()) +"')";
        System.out.println(sql);
       int i = entityManager.createNativeQuery(sql).executeUpdate();
       System.out.println(i);
        return i;
    }


    public List findAllByLs(String tables,String startTime,String endTime,String sb){
        List list = new ArrayList<>();
        String sql = tables+" AND updatetime BETWEEN '"+startTime+"' and '"+endTime+"'";
        System.out.println(sql);
        list = entityManager.createNativeQuery(sql).getResultList();
        return list;

    }

}
