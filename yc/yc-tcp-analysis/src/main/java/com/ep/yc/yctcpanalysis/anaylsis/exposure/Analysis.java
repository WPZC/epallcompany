package com.ep.yc.yctcpanalysis.anaylsis.exposure;

import com.ep.yc.yctcpanalysis.service.AnalysisMsgAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/17 15:02
 */
@RestController
@RequestMapping("/anaylsis")
public class Analysis {

    @Autowired
    private AnalysisMsgAll analysisMsgAll;

    @PostMapping("/request_sb")
    @ResponseBody
    public void request_sb(@RequestParam("message") String message) {

        analysisMsgAll.acceptedAnalysisMsg(message);

    }


}
