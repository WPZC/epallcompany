package com.ep.yc.yctcpanalysis.entity;

import java.util.Date;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-21 
 */

public class YcAlarmInfoEntity {


	private Long id;

	/**
	 * 报警标题
	 */
	private String alarmTitle;

	/**
	 * 报警内容
	 */
	private String alarmContent;

	/**
	 * 报警时间
	 */
	private Date alarmTime;

	/**
	 * 报警类型
	 */
	private String alarmType;

	/**
	 * 报警周期
	 */
	private String alarmCycle;

	/**
	 * 阈值
	 */
	private String alarmThresholdValue;

	/**
	 * 设备名称
	 */
	private String deviceName;

	/**
	 * 公司名称
	 */
	private String enterpriseName;

	/**
	 * 是否处理
	 */
	private Long isProcessing;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlarmTitle() {
		return this.alarmTitle;
	}

	public void setAlarmTitle(String alarmTitle) {
		this.alarmTitle = alarmTitle;
	}

	public String getAlarmContent() {
		return this.alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}

	public Date getAlarmTime() {
		return this.alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getAlarmType() {
		return this.alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}

	public String getAlarmCycle() {
		return this.alarmCycle;
	}

	public void setAlarmCycle(String alarmCycle) {
		this.alarmCycle = alarmCycle;
	}

	public String getAlarmThresholdValue() {
		return this.alarmThresholdValue;
	}

	public void setAlarmThresholdValue(String alarmThresholdValue) {
		this.alarmThresholdValue = alarmThresholdValue;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Long getIsProcessing() {
		return this.isProcessing;
	}

	public void setIsProcessing(Long isProcessing) {
		this.isProcessing = isProcessing;
	}

}
