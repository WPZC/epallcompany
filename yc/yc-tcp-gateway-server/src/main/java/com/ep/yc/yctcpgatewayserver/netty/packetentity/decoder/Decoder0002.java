package com.ep.yc.yctcpgatewayserver.netty.packetentity.decoder;

import com.ep.yc.yctcpgatewayserver.netty.packetentity.Packet;
import lombok.Data;

/**
 * @Author WQY
 * @Date 2019/11/11 17:10
 * @Version 1.0
 */
@Data
public class Decoder0002 extends Packet {

    /**
     * 鉴权码
     */
    private String authenticationCode;
    /**
     * 设备编号
     */
    private String sbid;
    /**
     * 终端消息ID
     */
    private String xxid;

}
