package com.epyc.ycdbbase.db_w.dao;

import com.epyc.ycdbbase.entity.YcOrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WQY
 * @date 2019/9/10 16:11
 */
public interface Yc_Organization_Jpa extends JpaRepository<YcOrganizationEntity,Integer> {

    @Query(nativeQuery = true,value = "select * from yc_organization where  organization_serial_num like ?1")
    List<YcOrganizationEntity> findLikeOrganizationName(String organization_serial_num);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,value = "DELETE FROM yc_organization WHERE organization_serial_num LIKE ?1")
    int deleteLikeOrganizationName(String organization_num);
}
