package com.ep.yc.yctcpanalysis.service.impl.forward;

import com.ep.yc.yctcpanalysis.entity.YcRealtimeinfoEntity;
import com.ep.yc.yctcpanalysis.service.Forwarding;
import com.ep.yc.yctcpanalysis.util.HttpRequest;

import java.util.Date;

/**
 * @Author WQY
 * @Date 2019/11/18 16:10
 * @Version 1.0
 */
public class SendHeNanAnYang extends Forwarding {


    public SendHeNanAnYang(String address, String ip, String port) {
        super(address, ip, port);
    }

    @Override
    public void editMsg(YcRealtimeinfoEntity real, String pwz) {

        String p="userkey="+real.getYdiDeviceMn()+"&data="+real.getPm25()+
                ","+real.getPm10()+","+real.getAirTemperature()+","+real.getAirHumidity()+
                ","+real.getNoise()+","+real.getWindSpeed()+","+real.getWindDirection()+","+real.getAtmosphericPressure()+
                "&date="+new Date().getTime();
        super.sendMsg(p,"http","http://ayssp.tunnel.qydev.com/testpub");
    }
}
