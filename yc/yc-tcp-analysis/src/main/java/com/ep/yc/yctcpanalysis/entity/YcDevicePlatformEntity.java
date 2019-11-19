package com.ep.yc.yctcpanalysis.entity;

/**
 * @Author WQY
 * @Date 2019/10/9 15:55
 * @Version 1.0
 */
public class YcDevicePlatformEntity {


    private Long id;

    /**
     * 设备号
     */
    private String deviceNum;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 组织机构编号
     */
    private String organizationNum;

    /**
     * 组织机构名称
     */
    private String organizationName;


    /**
     * 对接平台
     */
    private String toPlatform;
    //device_mn

    /**
     * 设备MN
     */
    private String deviceMn;

    /**
     * 对接平台IP
     */
    private String toIp;

    /**
     * 对接平台IP端口
     */
    private String toPort;

    /**
     * 对接平台名称
     */
    private String toPlatformName;

    /**
     * 协议类型
     */
    private String agreementType;

    /**
     * 对接平台密码
     */
    private String pw;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

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

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

}
