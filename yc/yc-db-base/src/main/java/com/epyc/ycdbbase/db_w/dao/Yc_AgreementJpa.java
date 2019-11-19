package com.epyc.ycdbbase.db_w.dao;

import com.epyc.ycdbbase.entity.YcAgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author WQY
 * @Date 2019/10/10 9:55
 * @Version 1.0
 */
public interface Yc_AgreementJpa extends JpaRepository<YcAgreementEntity,Integer> {
}
