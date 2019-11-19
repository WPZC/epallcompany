package com.epyc.yctcpclient.exposure;

import com.epyc.yctcpclient.context.BeanUtils;
import com.epyc.yctcpclient.context.Variable;
import com.epyc.yctcpclient.netty.TimeClient;
import com.epyc.yctcpclient.utils.HexByte;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/18 14:42
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    private static final Logger logger = Logger.getLogger(ClientController.class.getName());

    @RequestMapping("/receive")
    @ResponseBody
    public String receive(@RequestParam("message") String message) throws UnsupportedEncodingException {

        String[] strings = message.split("!!!!!!!");
        message = HexByte.characterAi(strings[1],"HEXTOSTR");
//        int x1 = message.indexOf(";MN=");
//        int x2 = message.indexOf(";Flag");
//        String sb = message.substring(x1,x2).substring(4,message.substring(x1,x2).length());
        //YcPlatformInfoEntity ycp = sendDbBase.findBySb(sb);
        byte[] reqMsgByte = message.getBytes("UTF-8");
        ByteBuf reqByteBuf = Unpooled.buffer(reqMsgByte.length);
        reqByteBuf.writeBytes(reqMsgByte);
        Variable.map.get(strings[0]).writeAndFlush(reqByteBuf);
        logger.info(strings[2]+"转发数据成功："+message);
        return "1";
    }

    /**
     * 启动client
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping("/startClient")
    @ResponseBody
    public String startClient(@RequestParam("ip") String ip,@RequestParam("port") Integer port){

        try {
            BeanUtils.webnettyPool.execute(()->{
                new TimeClient().connect(ip, port);
                System.out.println("任务结束");
            });
            return "启动成功";
        }catch (Exception e){
            BeanUtils.sendDbBase.updatePlatformState(1L,ip,port+"");
            e.printStackTrace();
            return "启动失败";
        }

    }

    /**
     * 关闭客户端
     * @param ip
     * @param port
     * @return
     */
    @RequestMapping("/shutDownClient")
    @ResponseBody
    public String shutDownClient(@RequestParam("ip") String ip,@RequestParam("port") Integer port){

        try {
            Variable.map.get(ip+":"+port).channel().close();
            return "关闭成功";
        }catch (Exception e){
            e.printStackTrace();
            return "关闭失败";
        }

    }


    public static void main(String args[]){
        String message = "##0353ST=22;CN=2011;QN=201909262113836CST;PW=YCZXJC;MN=SHEB866;FLAG=0;CP=&&DATATIME=201909262113836;A34001-RTD=24.0,A34001-FLAG=N;A34002-RTD=17,A34002-FLAG=N;A34004-RTD=10,A34004-FLAG=N;A01001-RTD=22.6,A01001-FLAG=N;A01002-RTD=31.7,A01002-FLAG=N;A01006-RTD=4.0,A01006-FLAG=N;A01007-RTD=7.4,A01007-FLAG=N;A01008-RTD=0.0,A01008-FLAG=N;LEQ-RTD=39.4,LEQ-FLAG=N;&&7F80\n";
        System.out.println(message.indexOf(";MN="));
        System.out.println(message.indexOf(";Flag="));

        int x1 = message.indexOf(";MN=");
        int x2 = message.indexOf(";FLAG=0");
        System.out.println(message.substring(x1,x2).substring(4,message.substring(x1,x2).length()));
    }
}



