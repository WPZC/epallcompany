package com.ep.yc.ycview.service;

import com.ep.yc.ycview.entity.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author WQY
 * @date 2019/9/18 18:36
 */
public interface DataMangementService {

    /**
     * 获取地图数据
     * @param orgNum
     * @return
     */
    List<Object>  getdeviceinfolist(String orgNum);


    /**
     * 获取地图数据
     * @param orgNum
     * @return
     */
    YcRealtimeinfoEntity  getavgrealtimeinfo(String orgNum);

    /**
     * 获取每小时数据
     * @return
     */
    List<Object> findByDayMsg(String orgNum);


    /**
     * 获取所有历史数据
     * @return
     */
    List<Object> findAllByLs(String startTime,String endTime,String sb);

    /**
     *
     /**
     * 获取实时数据列表
     * @param orgNum 组织机构编码
     * @return
     */
    List<Object> getrealtimeinfo(String orgNum);

    /**
     * 获取报警数据列表
     * @param orgNum
     * @return
     */
    List<YcAlarmInfoEntity> getAlarminfoList(String orgNum,String startTime,String endTime,String deviceNum);

    /**
     * 修改报警信息
     * @param id
     * @param msg
     * @return
     */
    String updateById(long id, String msg, MultipartFile file);

    /**
     * 获取视频地址编码
     * @param orgCode
     * @return
     */
    String[] getVideoUrl(String orgCode);

    /**
     * 获取视频地址编码
     * @return
     */
    List<YcAgreementEntity> getAgreementTypes();

    /**
     * 根据设备编号查询
     * @return
     */
    List<YcRealtimeinfoEntity> getRealTimeInfoD(String sbid);

    /**
     * 平台下发控制以及设置参数
     * @param msg
     * @param sbid
     * @return
     */
    String setParams(String msg,String sbid);
}


