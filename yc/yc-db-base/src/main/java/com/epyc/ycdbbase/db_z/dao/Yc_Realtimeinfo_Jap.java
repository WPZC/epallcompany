package com.epyc.ycdbbase.db_z.dao;

import com.epyc.ycdbbase.entity.YcRealtimeinfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/12 11:12
 */
public interface Yc_Realtimeinfo_Jap extends JpaRepository<YcRealtimeinfoEntity, Integer> {


    List<YcRealtimeinfoEntity> findByYdiDeviceNum(String ydiDeviceNum);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,value = "update yc_realtimeinfo set ydi_device_name = ?1 , ydi_belong_to_enterprise = ?2 ," +
            " pm25 = ?3 , pm10 = ?4 , noise = ?5 , air_temperature = ?6 , air_humidity = ?7 , " +
            " wind_speed = ?8 , wind_direction = ?9 , tsp = ?10 , oxygen_factor = ?11 , atmospheric_pressure = ?12 ," +
            " updatetime = ?13,ydi_device_mn=?15,longitude=?16,latitude=?17 where ydi_device_num = ?14")
    int update(String ydiDeviceName, String ydiBelongToEnterprise,
               String pm25, String pm10, String noise, String airTemperature,
               String airHumidity,
               double windSpeed, String windDirection, String tsp, String oxygenFactor,
               String atmosphericPressure, Date updatetime,String ydiDeviceNum,String mn,
               String longitude,String latitude);


}
