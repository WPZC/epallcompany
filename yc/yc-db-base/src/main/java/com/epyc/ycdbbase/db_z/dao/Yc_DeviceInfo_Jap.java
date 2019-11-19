package com.epyc.ycdbbase.db_z.dao;

import com.epyc.ycdbbase.entity.YcDeviceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/12 11:12
 */
public interface Yc_DeviceInfo_Jap extends JpaRepository<YcDeviceInfoEntity, Integer> {

    YcDeviceInfoEntity findByDeviceNum(String deviceNum);


}
