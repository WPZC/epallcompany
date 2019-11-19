package com.epyc.ycdbbase.db_w.dao;

import com.epyc.ycdbbase.entity.YcRealtimeinfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author WQY
 * @date 2019/9/19 9:40
 */
public interface Yc_RealTime_Jpa extends JpaRepository<YcRealtimeinfoEntity,Integer> {


    /**
     * 根据设备号查询
     * @param deviceNum
     * @return
     */
    YcRealtimeinfoEntity findByYdiDeviceNum(String deviceNum);

}
