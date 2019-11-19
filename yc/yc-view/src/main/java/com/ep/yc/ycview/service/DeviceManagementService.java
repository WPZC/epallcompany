package com.ep.yc.ycview.service;

import com.ep.yc.ycview.entity.YcDeviceInfoEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

/**
 * @author WQY
 * @date 2019/9/12 14:46
 */
public interface DeviceManagementService {
    /**
     * 保存设备信息
     * @param ycDeviceInfoEntity
     * @return
     */
    String saveDeviceInfo(YcDeviceInfoEntity ycDeviceInfoEntity);

    /**
     * 根据设备号删除
     * @param DeviceNum
     * @return
     */
    String deleteByDeviceNum(String DeviceNum);

    /**
     * 获取设备列表
     * @return
     */
    List<YcDeviceInfoEntity> getdevicelist(String orgCode) throws ParseException;

    /**
     * 根据设备号查询
     * @param devNum
     * @return
     */
    YcDeviceInfoEntity findByDeviceNum(String devNum);

    /**
     * 修改设备
     * @param ycDeviceInfoEntity
     * @return
     */
    String updateDeviceInfo(YcDeviceInfoEntity ycDeviceInfoEntity);



}
