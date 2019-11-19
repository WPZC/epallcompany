package com.ep.yc.yctcpgatewayserver.netty.packetentity.ecoder;

import com.ep.yc.yctcpgatewayserver.netty.packetentity.Packet;
import lombok.Data;

/**
 * @Author WQY
 * @Date 2019/11/15 11:40
 * @Version 1.0
 */
@Data
public class Platform7002 extends Packet {

    private String sbid;

    private String body;

}
