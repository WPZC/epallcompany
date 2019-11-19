package com.ep.yc.yctcpanalysis.cache;

import com.ep.yc.yctcpanalysis.entity.YcDevicePlatformEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author WQY
 * @Date 2019/10/9 16:06
 * @Version 1.0
 */
public class ContextDevicePlatform {


    /**
     * 老协议缓存
     * 设备列表缓存
     * 含对接平台和ip
     */
    public static HashMap<String,YcDevicePlatformEntity> devicePlatformEntityHashMap = new HashMap<String, YcDevicePlatformEntity>();
    /**
     * 212协议缓存
     * 设备列表缓存
     * 含对接平台和ipMN
     */
    public static HashMap<String,YcDevicePlatformEntity> devicePlatformEntityHashMapMn = new HashMap<String, YcDevicePlatformEntity>();

}
