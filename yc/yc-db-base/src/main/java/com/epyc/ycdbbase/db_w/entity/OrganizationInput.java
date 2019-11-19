package com.epyc.ycdbbase.db_w.entity;

/**
 * @author WQY
 * @date 2019/9/12 10:59
 */
public class OrganizationInput {


    //机构名称
    private String organization_name;
    //上级机构名称
    private String uporganization_name;
    //上级机构编号
    private String uporganization_serial_num;

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
