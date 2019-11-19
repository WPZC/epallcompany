package com.epyc.ycdbbase.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-21 
 */

@Entity
@Table ( name ="yc_alarm_info" )
public class YcAlarmInfoEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 报警标题
	 */
   	@Column(name = "alarm_title" )
	private String alarmTitle;

	/**
	 * 报警内容
	 */
   	@Column(name = "alarm_content" )
	private String alarmContent;

	/**
	 * 报警时间
	 */
   	@Column(name = "alarm_time" )
	private Date alarmTime;

	/**
	 * 报警类型
	 */
   	@Column(name = "alarm_type" )
	private String alarmType;

	/**
	 * 报警周期
	 */
   	@Column(name = "alarm_cycle" )
	private String alarmCycle;

	/**
	 * 阈值
	 */
   	@Column(name = "alarm_threshold_value" )
	private String alarmThresholdValue;

	/**
	 * 设备名称
	 */
   	@Column(name = "device_name" )
	private String deviceName;

	/**
	 * 公司名称
	 */
   	@Column(name = "enterprise_name" )
	private String enterpriseName;

	/**
	 * 是否处理
	 */
   	@Column(name = "is_processing" )
	private Long isProcessing;

	/**
	 * 处理意见
	 */
	@Column(name = "processing_msg" )
	private String processingMsg;

	/**
	 * 图片路径
	 */
	@Column(name = "pic_url" )
	private String picUrl;

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

	public String getProcessingMsg() {
		return processingMsg;
	}

	public void setProcessingMsg(String processingMsg) {
		this.processingMsg = processingMsg;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
