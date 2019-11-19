package com.epyc.ycdbbase.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author WQY
 * @Date 2019/10/9 15:55
 * @Version 1.0
 */
@Entity
@Table ( name ="yc_device_platform" )
public class YcDevicePlatformEntity {

    @Id
    @Column(name = "id" )
    private Long id;

    /**
     * 设备号
     */
    @Column(name = "device_num" )
    private String deviceNum;

    /**
     * 设备名称
     */
    @Column(name = "device_name" )
    private String deviceName;

    /**
     * 组织机构编号
     */
    @Column(name = "organization_num" )
    private String organizationNum;

    /**
     * 组织机构名称
     */
    @Column(name = "organization_name" )
    private String organizationName;


    /**
     * 对接平台
     */
    @Column(name = "to_platform" )
    private String toPlatform;
    //device_mn

    /**
     * 设备MN
     */
    @Column(name = "device_mn" )
    private String deviceMn;

    /**
     * 对接平台IP
     */
    @Column(name = "to_ip" )
    private String toIp;

    /**
     * 对接平台IP端口
     */
    @Column(name = "to_port" )
    private String toPort;

    /**
     * 对接平台名称
     */
    @Column(name = "to_platform_name" )
    private String toPlatformName;

    /**
     * 协议类型
     */
    @Column(name = "agreement_type" )
    private String agreementType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOrganizationNum() {
        return organizationNum;
    }

    public void setOrganizationNum(String organizationNum) {
        this.organizationNum = organizationNum;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getToPlatform() {
        return toPlatform;
    }

    public void setToPlatform(String toPlatform) {
        this.toPlatform = toPlatform;
    }

    public String getDeviceMn() {
        return deviceMn;
    }

    public void setDeviceMn(String deviceMn) {
        this.deviceMn = deviceMn;
    }

    public String getToIp() {
        return toIp;
    }

    public void setToIp(String toIp) {
        this.toIp = toIp;
    }

    public String getToPort() {
        return toPort;
    }

    public void setToPort(String toPort) {
        this.toPort = toPort;
    }

    public String getToPlatformName() {
        return toPlatformName;
    }

    public void setToPlatformName(String toPlatformName) {
        this.toPlatformName = toPlatformName;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }
}
