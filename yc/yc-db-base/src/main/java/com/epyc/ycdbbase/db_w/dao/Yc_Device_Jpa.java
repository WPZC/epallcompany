package com.epyc.ycdbbase.db_w.dao;

import com.epyc.ycdbbase.entity.YcDeviceInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WQY
 * @date 2019/9/17 10:10
 */
public interface Yc_Device_Jpa extends JpaRepository<YcDeviceInfoEntity,Integer> {

    /**
     * 根据组织机构删除设备
     * @param organization_num
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,value = "DELETE FROM yc_device_info WHERE organization_num LIKE ?1")
    int deleteLikeOrganizationName(String organization_num);

    /**
     * 根据设备编号查询
     * @param deviceNum
     * @return
     */
    YcDeviceInfoEntity findByDeviceNum(String deviceNum);

    /**
     * 根据设备号删除设备
     * @param deviceNum
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    int deleteByDeviceNum(String deviceNum);

    /**
     * 根据组织机构模糊查询
     * @param orgNum
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT * FROM `yc_device_info` WHERE organization_num LIKE ?1")
    List<YcDeviceInfoEntity> findByLikeOrOrganizationNum(String orgNum);

    /**
     * 根据MN查询
     * @param mn
     * @return
     */
    YcDeviceInfoEntity findByDeviceMn(String mn);


}
