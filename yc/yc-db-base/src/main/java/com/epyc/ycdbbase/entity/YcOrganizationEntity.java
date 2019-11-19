package com.epyc.ycdbbase.entity;

import javax.persistence.*;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-19 
 */

@Entity
@Table ( name ="yc_organization" )
public class YcOrganizationEntity  {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 组织机构名称
	 */
   	@Column(name = "organization_name" )
	private String organizationName;

	/**
	 * 组织机构编号
	 */
   	@Column(name = "organization_serial_num" )
	private String organizationSerialNum;

	/**
	 * 紧急联系人
	 */
   	@Column(name = "contact_person_name" )
	private String contactPersonName;

	/**
	 * 紧急联系人手机号
	 */
   	@Column(name = "contact_person_phone" )
	private String contactPersonPhone;

	/**
	 * 监管类型
	 */
	@Column(name = "supervision_type" )
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
