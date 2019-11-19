package com.ep.yc.ycview.entity;

import java.util.List;

/**
 * @author WQY
 * @date 2019/9/16 9:16
 */
public class OrgEntity {

    private String name;

    private List<OrgEntity> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrgEntity> getList() {
        return list;
    }

    public void setList(List<OrgEntity> list) {
        this.list = list;
    }
}
