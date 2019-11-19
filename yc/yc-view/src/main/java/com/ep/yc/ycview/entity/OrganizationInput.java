package com.ep.yc.ycview.entity;

/**
 * @author WQY
 * @date 2019/9/12 10:59
 */
public class OrganizationInput {


    //组织机构名称
    private String organization_name;
    //上级组织机构名称
    private String uporganization_name;
    //上级组织机构编号
    private String uporganization_serial_num;
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
     */
    private String supervisionType;

    public String getSupervisionType() {
        return supervisionType;
    }

    public void setSupervisionType(String supervisionType) {
        this.supervisionType = supervisionType;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public String getUporganization_name() {
        return uporganization_name;
    }

    public void setUporganization_name(String uporganization_name) {
        this.uporganization_name = uporganization_name;
    }

    public String getUporganization_serial_num() {
        return uporganization_serial_num;
    }

    public void setUporganization_serial_num(String uporganization_serial_num) {
        this.uporganization_serial_num = uporganization_serial_num;
    }
}
