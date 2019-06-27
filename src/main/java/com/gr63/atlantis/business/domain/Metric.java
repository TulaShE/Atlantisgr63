/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.domain;

import java.util.Date;
import javax.persistence.*;
/**
 *
 * @author dev
 */
@Entity
@Table(name="metric")
public class Metric {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String value;
    
    @ManyToOne
    @JoinColumn(name = "id_device")
    private Device deviceMetric;

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Device getDeviceMetric() {
        return deviceMetric;
    }

    public void setDeviceMetric(Device deviceMetric) {
        this.deviceMetric = deviceMetric;
    }
    
    
}
