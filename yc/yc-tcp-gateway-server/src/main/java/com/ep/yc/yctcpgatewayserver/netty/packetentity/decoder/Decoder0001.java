package com.ep.yc.yctcpgatewayserver.netty.packetentity.decoder;

import com.ep.yc.yctcpgatewayserver.netty.packetentity.Packet;
import lombok.Data;

/**
 * @Author WQY
 * @Date 2019/11/11 14:35
 * @Version 1.0
 */
@Data
public class Decoder0001 extends Packet {

    /**
     * 应答ID
     */
    private String ydId;

    /**
     * 应答结果
     */
    private String ydJg;

    /**
     * 设备编号
     */
    private String sbid;


}
