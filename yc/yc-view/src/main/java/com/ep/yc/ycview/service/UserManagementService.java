package com.ep.yc.ycview.service;

import com.ep.yc.ycview.entity.OrganizationInput;
import com.ep.yc.ycview.entity.YcOrganizationEntity;
import com.ep.yc.ycview.entity.YcPlatformInfoEntity;
import com.ep.yc.ycview.entity.YcUserEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author WQY
 * @date 2019/9/10 15:14
 */
public interface UserManagementService {


    /**
     * 添加用户
     * @param ycUserEntity
     * @return
     */
    String addUser(YcUserEntity ycUserEntity);

    /**
     * 获取用户列表
     * @param orgnum
     * @return
     */
    List<YcUserEntity> getuserlist(String orgnum);

    /**
     * 获取平铺组织机构列表
     * @param orgnum
     * @return
     */
    List<YcOrganizationEntity> getorglist(String orgnum);



    /**
     * 删除用户
     * @param username
     * @return
     */
    String deleteUser(String username);

    /**
     * 获取组织机构信息
     * @return
     */
    List<HashMap<String,Object>> findByOrganizationList(String orgNum);


    /**
     * 添加组织机构
     * @param organizationInput
     * @return
     */
    String addOrganization(OrganizationInput organizationInput) throws Exception;

    /**
     * 删除组织机构
     * @param orgNum
     * @return
     */
    String deleteOrg(String orgNum) throws Exception;


    /**
     * 添加对接平台
     * @param ycPlatformInfoEntity
     * @return
     */
    String addtoplatform(YcPlatformInfoEntity ycPlatformInfoEntity) throws Exception;

    /**
     * 删除对接平台
     * @param platform
     * @return
     */
    String deletetoplatform(String platform) throws Exception;

    /**
     * 获取对接平台列表
     * @return
     */
    List<YcPlatformInfoEntity> gettoplatformlist() throws Exception;

}
