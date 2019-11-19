package com.epyc.ycdbbase.db_w.dao;

import com.epyc.ycdbbase.entity.YcRealtimeinfoEntity;
import com.epyc.ycdbbase.entity.YcSendMsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author WQY
 * @Date 2019/11/15 15:14
 * @Version 1.0
 */
public interface Yc_Send_Msg_Jpa extends JpaRepository<YcSendMsgEntity,Integer> {
}
