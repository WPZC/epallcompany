package com.epyc.ycdbbase.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  WQY
 * @Date 2019-11-15 
 */

@Entity
@Table ( name ="yc_send_msg" )
public class YcSendMsgEntity  {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 设备编号
	 */
   	@Column(name = "device_num" )
	private String deviceNum;

	/**
	 * 发送消息的消息体
	 */
   	@Column(name = "msg" )
	private String msg;

	/**
	 * 发送时间
	 */
   	@Column(name = "send_time" )
	private Date sendTime;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeviceNum() {
		return this.deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}
