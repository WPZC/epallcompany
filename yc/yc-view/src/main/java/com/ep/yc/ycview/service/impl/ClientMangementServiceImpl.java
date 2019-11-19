package com.ep.yc.ycview.service.impl;

import com.ep.yc.ycview.requestdb.SendCloent;
import com.ep.yc.ycview.service.ClientMangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WQY
 * @date 2019/9/21 16:04
 */
@Service
public class ClientMangementServiceImpl implements ClientMangementService {

    @Autowired
    private SendCloent sendCloent;

    @Override
    public String startClient(String ip, Integer port) {
        return sendCloent.startClient(ip, port);
    }

    @Override
    public String shutDownClient(String ip, Integer port) {
        return sendCloent.shutDownClient(ip, port);
    }
}
