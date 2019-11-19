package com.ep.yc.ycview.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author WQY
 * @date 2019/9/10 15:26
 */
@ApiModel(value = "outView",description = "返回数据")
public class OutView<T> {

    //状态，0成功，1失败
    @ApiModelProperty(name = "state",value = "状态，0成功，1失败",dataType = "int")
    private Integer state;
    //消息体
    @ApiModelProperty(name = "state",value = "消息体",dataType = "Object")
    private T msg;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }
}
