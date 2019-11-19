package com.epyc.ycdbbase.db_w.dao;

import com.epyc.ycdbbase.entity.YcPlatformInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WQY
 * @date 2019/9/17 18:20
 */
public interface Yc_Platform_Jpa extends JpaRepository<YcPlatformInfoEntity,Integer> {

    /**
     * 根据对接平台名称删除
     * @param platformname
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    int deleteByToPlatformName(String platformname);

    /**
     * 根据对接平台名称查询
     * @param platformname
     * @return
     */
    YcPlatformInfoEntity findByToPlatformName(String platformname);


    @Query(nativeQuery = true,value = "select b.* from yc_device_info a,yc_platform_info b WHERE device_num = ?1 AND a.to_platform = b.to_platform_name")
    YcPlatformInfoEntity findBySb(String sb);

    /**
     * 修改对接平台state
     * @param state
     * @param toPort
     * @param toIp
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update YcPlatformInfoEntity y set y.state=?1 where y.toPort=?2 and y.toIp=?3")
    int updatePlatformState(Long state,String toPort,String toIp);
}
