package com.ep.yc.ycview.entity;

import java.util.Date;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-12 
 */

public class YcUserEntity {

	private Long id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 保存时间
	 */
	private String savetime;

	/**
	 * 手机号
	 */
	private String iphone;

	/**
	 * 权限,1是最大权限，2是最小权限
	 */
	private Long authority;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 组织机构编号
	 */
	private String organizationNum;

	/**
	 * 组织机构名称
	 */
	private String organizationName;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSavetime() {
		return this.savetime;
	}

	public void setSavetime(String savetime) {
		this.savetime = savetime;
	}

	public String getIphone() {
		return this.iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public Long getAuthority() {
		return this.authority;
	}

	public void setAuthority(Long authority) {
		this.authority = authority;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOrganizationNum() {
		return this.organizationNum;
	}

	public void setOrganizationNum(String organizationNum) {
		this.organizationNum = organizationNum;
	}

	public String getOrganizationName() {
		return this.organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

}
