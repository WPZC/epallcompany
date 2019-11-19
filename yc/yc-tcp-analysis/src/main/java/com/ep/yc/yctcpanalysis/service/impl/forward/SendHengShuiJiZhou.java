package com.ep.yc.yctcpanalysis.service.impl.forward;

import com.ep.yc.yctcpanalysis.entity.YcRealtimeinfoEntity;
import com.ep.yc.yctcpanalysis.service.Forwarding;
import com.ep.yc.yctcpanalysis.util.DateUtil;
import com.ep.yc.yctcpanalysis.util.TextUtil;
import org.apache.tomcat.util.buf.HexUtils;
import sun.misc.CRC16;

/**
 * @Author WQY
 * @Date 2019/11/18 16:10
 * @Version 1.0
 */
public class SendHengShuiJiZhou extends Forwarding {


    public SendHengShuiJiZhou(String address, String ip, String port) {
        super(address, ip, port);
    }

    @Override
    public void editMsg(YcRealtimeinfoEntity real, String pwz) {

//        String msg="15"+ TextUtil.intToHex(Integer.parseInt(real.getYdiDeviceMn(),16),2)+  TextUtil.intToHex(Integer.parseInt(real.getAirTemperature()),2)+  TextUtil.intToHex(Integer.parseInt(real.getAirHumidity()),2)+"00000000"+
//                TextUtil.intToHex(Math.round(real.getWindSpeed()),2) +TextUtil.intToHex(Integer.parseInt(real.getWindDirection()),2)+"0000"+TextUtil.intToHex(Integer.parseInt(real.getPm10()),2)
//                +TextUtil.intToHex(Integer.parseInt(real.getNoise()),2)+"000000000000"+TextUtil.intToHex(Integer.parseInt(real.getJingdu(),16),2)+TextUtil.intToHex(Integer.parseInt(si.getWeidu(),16),2)+
//                TextUtil.intToHex((int)si.getMp25(),2);
//        msg=msg+CRC16.CRC_CCITT2(HexUtils.hexStringToByte(msg));

        super.sendMsg("","tcp","");
    }
}
