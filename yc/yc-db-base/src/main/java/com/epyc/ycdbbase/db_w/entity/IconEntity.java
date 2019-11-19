package com.epyc.ycdbbase.db_w.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * @author WQY
 * @date 2019/9/19 10:31
 */
@Entity
public class IconEntity {

    @Id
    @Column(name = "hours" )
    private String hours;
    @Column(name = "count" )
    private BigInteger count;
    @Column(name = "pm25" )
    private Double pm25;
    @Column(name = "pm10" )
    private Double pm10;

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    public Double getPm25() {
        return pm25;
    }

    public void setPm25(Double pm25) {
        this.pm25 = pm25;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }
}
