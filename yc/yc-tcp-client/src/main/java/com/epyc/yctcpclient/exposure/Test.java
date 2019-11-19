package com.epyc.yctcpclient.exposure;

import com.epyc.yctcpclient.requestdb.SendDbBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WQY
 * @date 2019/9/18 11:46
 */
@RestController
public class Test {

    @Autowired
    private SendDbBase sendDbBase;

    @RequestMapping("/testclient")
    @ResponseBody
    public String test1(){
        return sendDbBase.gettoplatformlist();
    }

}
