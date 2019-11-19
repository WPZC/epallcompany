package com.ep.yc.ycview.entity;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-19 
 */


public class YcOrganizationEntity {


	private Long id;

	/**
	 * 组织机构名称
	 */
	private String organizationName;

	/**
	 * 组织机构编号
	 */
	private String organizationSerialNum;

	/**
	 * 紧急联系人
	 */
	private String contactPersonName;

	/**
	 * 紧急联系人手机号
	 */
	private String contactPersonPhone;

	/**
	 * 监管类型
	 * @return
	 */
	private String supervisionType;

	public String getSupervisionType() {
		return supervisionType;
	}

	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return this.organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationSerialNum() {
		return this.organizationSerialNum;
	}

	public void setOrganizationSerialNum(String organizationSerialNum) {
		this.organizationSerialNum = organizationSerialNum;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getContactPersonPhone() {
		return this.contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}

}
