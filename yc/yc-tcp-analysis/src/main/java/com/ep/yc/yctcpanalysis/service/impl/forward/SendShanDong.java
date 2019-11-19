package com.ep.yc.yctcpanalysis.service.impl.forward;

import com.ep.yc.yctcpanalysis.entity.YcRealtimeinfoEntity;
import com.ep.yc.yctcpanalysis.service.Forwarding;
import com.ep.yc.yctcpanalysis.util.DateUtil;
import com.ep.yc.yctcpanalysis.util.HttpRequest;

/**
 * @Author WQY
 * @Date 2019/11/18 15:29
 * @Version 1.0
 */
public class SendShanDong extends Forwarding {


    public SendShanDong(String address, String ip, String port) {
        super(address, ip, port);
    }

    @Override
    public void editMsg(YcRealtimeinfoEntity real, String pwz) {

        String p="001,371702102100001,"+ DateUtil.getCurDateStr(DateUtil.DEFAULT_INTEGER_FORMAT)+","+
                real.getPm25()+","+real.getPm10()+","+real.getNoise()+","+real.getAirTemperature()+","+real.getAirHumidity()+
                ","+real.getWindSpeed()+","+real.getWindDirection()+","+(int)Double.parseDouble(real.getAtmosphericPressure()+"")+","+(int)Double.parseDouble(real.getTsp()+"")+",0,0";
        super.sendMsg(p,"http","http://pm.inheze.cn:9053/ycjc/api/rdata/");
    }
}
