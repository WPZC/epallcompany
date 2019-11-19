package com.ep.yc.yctcpgatewayserver.netty.packetentity.ecoder;

import com.ep.yc.yctcpgatewayserver.netty.packetentity.Packet;
import lombok.Data;

/**
 * @Author WQY
 * @Date 2019/11/11 17:10
 * @Version 1.0
 */
@Data
public class ResEcoder0002 extends Packet {

    //鉴权码
    private String authenticationCode;

}
