package com.epyc.ycdbbase.db_w.dao;

import com.epyc.ycdbbase.entity.YcDeviceInfoEntity;
import com.epyc.ycdbbase.entity.YcDeviceInfoRealtimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author WQY
 * @date 2019/9/18 19:04
 */
public interface Yc_Device_Realtime_Jpa extends JpaRepository<YcDeviceInfoRealtimeEntity,Integer> {

    /**
     * 根据传入组织机构id查该组织机构id下的所有设备
     * @param orgNum
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT * FROM `yc_devcie_realtime` where organization_num IN(SELECT organization_serial_num FROM `yc_organization` WHERE organization_serial_num LIKE ?1)")
    List<YcDeviceInfoRealtimeEntity> findByOrganizationNumList(String orgNum);

}
