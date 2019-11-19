package com.epyc.ycdbbase.db_w.dao;

import com.epyc.ycdbbase.entity.YcOrganizationEntity;
import com.epyc.ycdbbase.entity.YcUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WQY
 * @date 2019/9/9 15:28
 */
public interface Yc_User_Jpa extends JpaRepository<YcUserEntity,Integer> {

    YcUserEntity findByUsernameAndPassword(String username,String password);

    YcUserEntity findByUsername(String username);

    /**
     * 删除用户
     * @param username
     * @return
     */
    //注解用于提交事务，若没有带上这句，会报事务异常提示。
    @Transactional
    //自动清除实体里保存的数据。
    @Modifying(clearAutomatically = true)
    int deleteByUsername(String username);


    /**
     * 按组织机构删除用户
     * @param organization_num
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true,value = "DELETE FROM yc_user WHERE organization_num LIKE ?1")
    int deleteLikeOrganizationName(String organization_num);


    /**
     * 根据组织机构查询对应用户列表
     * @param organization_num
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT * FROM yc_user WHERE organization_num LIKE ?1 ")
    List<YcUserEntity> findByOrganizationNumList(String organization_num);

}
