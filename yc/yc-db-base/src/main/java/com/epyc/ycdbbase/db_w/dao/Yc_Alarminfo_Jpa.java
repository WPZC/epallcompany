package com.epyc.ycdbbase.db_w.dao;

import com.epyc.ycdbbase.entity.YcAlarmInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WQY
 * @date 2019/9/21 14:49
 */
public interface Yc_Alarminfo_Jpa extends JpaRepository<YcAlarmInfoEntity,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM `yc_alarm_info` where device_name in(SELECT device_num FROM `yc_device_info` where organization_num LIKE ?1) ORDER BY alarm_time DESC LIMIT 0,20")
    List<YcAlarmInfoEntity> findByOrgNum(String orgnum);

    @Query(nativeQuery = true,value = "SELECT * FROM `yc_alarm_info` where device_name in(SELECT device_num FROM `yc_device_info` where organization_num LIKE ?1) AND alarm_time>?2 AND alarm_time<?3 AND device_name = ?4 ORDER BY alarm_time DESC")
    List<YcAlarmInfoEntity> findByOrgNumAndStartTimeAndEndTimeList(String orgnum,String startTime,String endTime,String deviceNum);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update YcAlarmInfoEntity y set y.processingMsg=?1,y.picUrl=?3,y.isProcessing=1 where y.id=?2")
    int updateById(String msg,long id,String pic);

}
