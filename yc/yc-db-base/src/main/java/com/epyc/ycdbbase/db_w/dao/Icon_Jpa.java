package com.epyc.ycdbbase.db_w.dao;

import com.epyc.ycdbbase.db_w.entity.IconEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WQY
 * @date 2019/9/19 10:34
 */
@Component
public class Icon_Jpa{

    //    @Query(nativeQuery = true,value = "select DATE_FORMAT(updatetime,'%Y%m%d%H') hours,count(id) count,AVG(pm25) pm25,AVG(pm10) pm10 from ?1 group by hours;")
//    List<IconEntity> findByDayMsg(String tableName);
    @PersistenceContext
    EntityManager entityManager;

    public List<IconEntity> findByDayMsg(String tableName,String orgNum){

        String sql = "select DATE_FORMAT(updatetime,'%Y%m%d%H') hours,count(id) count,AVG(pm25) pm25,AVG(pm10) pm10 from "+tableName+" WHERE ydi_device_num IN(SELECT device_num FROM `yc_device_info` where organization_num LIKE '"+orgNum+"') group by hours";

        List<Object[]> rs = entityManager.createNativeQuery(sql).getResultList();

        List<IconEntity> iconEntities = new ArrayList<IconEntity>();
        for (int i = 0,num = rs.size();i<num;i++){
            IconEntity iconEntity = new IconEntity();
            iconEntity.setHours((String) rs.get(i)[0]);
            iconEntity.setCount(((BigInteger) rs.get(i)[1]));
            iconEntity.setPm25((double) rs.get(i)[2]);
            iconEntity.setPm10((double) rs.get(i)[3]);
            iconEntities.add(iconEntity);
        }
        return iconEntities;
    }


    public IconEntity findByDayMsgA(String tableName,String sbNum){

        String sql = "select DATE_FORMAT(updatetime,'%Y%m%d%H') hours,count(id) count,AVG(pm25) pm25,AVG(pm10) pm10 from "+tableName+" WHERE ydi_device_num = '"+sbNum+"' group by hours ORDER BY hours DESC LIMIT 0,1;";
        List<Object[]> rs = entityManager.createNativeQuery(sql).getResultList();

        IconEntity iconEntity = new IconEntity();
        for (int i = 0,num = rs.size();i<num;i++){
            iconEntity.setHours((String) rs.get(i)[0]);
            iconEntity.setCount(((BigInteger) rs.get(i)[1]));
            iconEntity.setPm25((double) rs.get(i)[2]);
            iconEntity.setPm10((double) rs.get(i)[3]);
        }
        return iconEntity;
    }

}
