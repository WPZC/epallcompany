package com.epyc.yctcpclient.context;

import com.epyc.yctcpclient.requestdb.SendDbBase;
import com.epyc.yctcpclient.utils.thread.ThreadPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author WQY
 * @date 2019/9/18 11:51
 */
public class BeanUtils {

    @Autowired
    public static SendDbBase sendDbBase = SpringUtils.getApplicationContext().getBean(SendDbBase.class);

    public static ThreadPoolExecutor webnettyPool = new ThreadPoolUtils().getNewInstance(10,15,
            "webnettyPool",10);
}
