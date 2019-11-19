package com.ep.yc.yctcpgatewayserver.netty.packetentity.ecoder;

import com.ep.yc.yctcpgatewayserver.netty.packetentity.Packet;
import lombok.Data;

/**
 * 平台通用应答
 * @Author WQY
 * @Date 2019/11/11 14:48
 * @Version 1.0
 */
@Data
public class ResEcoder0001 extends Packet {

    //应答ID
    private String ydId;
    //应答结果
    private String ydRs;
    //设备ID
    private String sbid;
    //应答时间
    private String time;

}
