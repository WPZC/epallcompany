package com.ep.yc.ycview.service;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author WQY
 * @date 2019/9/21 16:03
 */
public interface ClientMangementService {


    String startClient(String ip,Integer port);

    String shutDownClient(String ip,Integer port);


}
