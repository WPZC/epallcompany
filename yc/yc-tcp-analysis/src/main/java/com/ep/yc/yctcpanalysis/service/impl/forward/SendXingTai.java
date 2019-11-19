package com.ep.yc.yctcpanalysis.service.impl.forward;

import com.ep.yc.yctcpanalysis.entity.YcRealtimeinfoEntity;
import com.ep.yc.yctcpanalysis.service.Forwarding;
import com.ep.yc.yctcpanalysis.util.DateUtil;
import com.ep.yc.yctcpanalysis.util.TextUtil;

/**
 * @Author WQY
 * @Date 2019/11/18 16:10
 * @Version 1.0
 */
public class SendXingTai extends Forwarding {


    public SendXingTai(String address, String ip, String port) {
        super(address, ip, port);
    }

    @Override
    public void editMsg(YcRealtimeinfoEntity real, String pwz) {

        String msg="ST=22;CN=2011;PW=123456;MN="+real.getYdiDeviceNum()+";CP=&&DataTime="+ DateUtil.getCurDateStr(DateUtil.DEFAULT_INTEGER_FORMAT) +";";
        String date="925-Rtd="+real.getPm25()+",925-Flag=N;" +
                "107-Rtd="+real.getPm10()+",107-Flag=N;" +
                "103-Rtd="+real.getTsp()+",103-Flag=N;" +
                "126-Rtd="+real.getAirTemperature()+",126-Flag=N;" +
                "128-Rtd="+real.getAirHumidity()+",128-Flag=N;";
        date+="129-Rtd="+real.getWindSpeed()+",129-Flag=N;" +
                "130-Rtd="+real.getWindDirection()+",130-Flag=N;" +
                "127-Rtd="+real.getAtmosphericPressure()+",127-Flag=N;";
        date+="926-Rtd=0,926-Flag=N;";
        date+="927-Rtd=0,927-Flag=N;";


        msg="##"+ TextUtil.leftMakeUp(""+date.length(),4)+msg+date+"&&\r\n";

        super.sendMsg(msg,"tcp","");
    }
}
