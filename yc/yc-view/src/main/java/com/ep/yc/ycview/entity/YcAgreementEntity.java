package com.ep.yc.ycview.entity;

/**
 * @Description  
 * @Author  WQY
 * @Date 2019-10-10 
 */


public class YcAgreementEntity {



	private Long id;

	/**
	 * 协议类型
	 */
	private String aAgreementType;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAAgreementType() {
		return this.aAgreementType;
	}

	public void setAAgreementType(String aAgreementType) {
		this.aAgreementType = aAgreementType;
	}

}
