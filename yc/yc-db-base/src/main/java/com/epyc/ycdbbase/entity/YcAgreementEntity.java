package com.epyc.ycdbbase.entity;

import javax.persistence.*;

/**
 * @Description  
 * @Author  WQY
 * @Date 2019-10-10 
 */

@Entity
@Table ( name ="yc_agreement" )
public class YcAgreementEntity  {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 协议类型
	 */
   	@Column(name = "a_agreement_type" )
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
