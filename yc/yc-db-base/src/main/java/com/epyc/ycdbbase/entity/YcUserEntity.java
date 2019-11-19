package com.epyc.ycdbbase.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  wqy
 * @Date 2019-09-12 
 */

@Entity
@Table ( name ="yc_user" )
public class YcUserEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 用户名
	 */
   	@Column(name = "username" )
	private String username;

	/**
	 * 密码
	 */
   	@Column(name = "password" )
	private String password;

	/**
	 * 保存时间
	 */
   	@Column(name = "savetime" )
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date savetime;

	/**
	 * 手机号
	 */
   	@Column(name = "iphone" )
	private String iphone;

	/**
	 * 权限,1是最大权限，2是最小权限
	 */
   	@Column(name = "authority" )
	private Long authority;

	/**
	 * 性别
	 */
   	@Column(name = "sex" )
	private String sex;

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

	public Date getSavetime() {
		return this.savetime;
	}

	public void setSavetime(Date savetime) {
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
