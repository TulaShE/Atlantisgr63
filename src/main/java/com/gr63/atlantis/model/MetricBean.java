/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.model;

import com.gr63.atlantis.business.domain.Metric;
import com.gr63.atlantis.business.logic.MetricServiceLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author dev
 */
@Named(value = "metricModel")
@SessionScoped
public class MetricBean implements Serializable {

    private Long id;
    private Long userId;
    private Date date;
    private String value;
    private String name;

    private List<Metric> listMetrics;
    
    @Inject
    private MetricServiceLocal metricService;
    private Metric metric;
    /**
     * Creates a new instance of MetricBean
     */
    public MetricBean() {
    }
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public Date getDate() {
        return date;
    }
    
    
    public List<Metric> getAllMetrics(Long deviceId)
    {
        return metricService.getMetricsByDevice(deviceId);
    }
    
    public void setValue()
    {
        
    }
}