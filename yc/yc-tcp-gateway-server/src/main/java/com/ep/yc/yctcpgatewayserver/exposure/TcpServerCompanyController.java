package com.ep.yc.yctcpgatewayserver.exposure;

import com.ep.yc.yctcpgatewayserver.netty.cache.CachePacket;
import com.ep.yc.yctcpgatewayserver.netty.packetentity.ecoder.Platform7002;
import com.ep.yc.yctcpgatewayserver.utils.HexByte;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author WQY
 * @Date 2019/11/15 11:05
 * @Version 1.0
 */

@Api(value = "自定义协议控制接口")
@RestController
@RequestMapping("/tcpservercompany")
public class TcpServerCompanyController {

    private Gson gson = new Gson();

    private SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");

    @RequestMapping(value = "/setParams",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "下发控制")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg",value = "json，key-value的消息体",dataType = "String",required = true),
            @ApiImplicitParam(name = "sbid",value = "设备编号",dataType = "String",required = true)
    })
    public String setParams(@RequestParam("msg") String msg,@RequestParam("sbid") String sbid){

        HashMap<String,String> map = new HashMap<String, String>();
        map = gson.fromJson(msg,map.getClass());

        String allHexStr = "";
        for (String key:map.keySet()){
            String body = map.get(key);
            String hexStr = "";
            switch (key){
                case "01":
                    hexStr = HexByte.characterAi(body,"STRTOHEX");
                    allHexStr += "01"+ HexByte.characterAi((hexStr.length()/2)+"","INTTOBYTE")+hexStr;
                    break;
                case "02":
                    hexStr = HexByte.characterAi(body,"INTTOWORD");
                    allHexStr += "02"+ HexByte.characterAi((hexStr.length()/2)+"","INTTOBYTE")+hexStr;
                    break;
                case "03":
                    hexStr = HexByte.characterAi(body,"INTTOWORD");
                    allHexStr += "03"+ HexByte.characterAi((hexStr.length()/2)+"","INTTOBYTE")+hexStr;
                    break;
                case "04":
                    hexStr = body;
                    allHexStr += "04"+ HexByte.characterAi((hexStr.length()/2)+"","INTTOBYTE")+hexStr;
                    break;
                case "05":
                    hexStr = body;
                    allHexStr += "05"+ HexByte.characterAi((hexStr.length()/2)+"","INTTOBYTE")+hexStr;
                    break;
            }
        }
        Platform7002 platform7002 = new Platform7002();
        platform7002.setBody(HexByte.characterAi(map.size()+"","INTTOBYTE")+format.format(new Date())+allHexStr);
        platform7002.setSbid(sbid);
        try {
            CachePacket.strCtxMap.get(sbid).writeAndFlush(platform7002);
        }catch (Exception e){
            return "下发失败";
        }
        return "下发成功";
    }

}
