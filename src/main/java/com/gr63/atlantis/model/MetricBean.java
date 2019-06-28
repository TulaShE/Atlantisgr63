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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

    @Inject
    private MetricServiceLocal metricService;
    
    private List<Metric> listMetrics;
    
    /**
     * Creates a new instance of MetricBean
     */
    public MetricBean() {
    }
    
    public String listMetrics(){
        listMetrics = metricService.getAllMetrics();
        
        for(int i = 0; i < listMetrics.size(); i++){
            System.out.println(listMetrics.get(i).getDate().toString());
            System.out.println(listMetrics.get(i).getValue());
            System.out.println(listMetrics.get(i).getDeviceMetric().getName());
            
        }
        return "index";
    }
}
