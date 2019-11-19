package com.epyc.ycdbbase.db_z.exposure;

import com.epyc.ycdbbase.db_w.dao.Yc_Platform_Jpa;
import com.epyc.ycdbbase.entity.YcPlatformInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author: zhuhaoyu
 * @Date: 2019/9/18 12:38
 */
@RestController
@RequestMapping("/clientToDb")
public class ClientController {
    @Autowired
    private Yc_Platform_Jpa yc_platform_jpa;

    @PostMapping("/findAll")
    @ResponseBody
    public List<YcPlatformInfoEntity> findAll(){
        List<YcPlatformInfoEntity> list = new ArrayList<>();
        try {
            list = yc_platform_jpa.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @PostMapping("/findBySb")
    @ResponseBody
    public YcPlatformInfoEntity findBySb(String sb){
        YcPlatformInfoEntity ycPlatformInfoEntity = new YcPlatformInfoEntity();
        try {
            ycPlatformInfoEntity = yc_platform_jpa.findBySb(sb);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ycPlatformInfoEntity;
    }
}
