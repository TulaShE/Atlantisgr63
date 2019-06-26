/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.logic;

import com.gr63.atlantis.business.domain.Metric;
import com.gr63.atlantis.integration.MetricDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author dev
 */
@Stateful
public class MetricService implements MetricServiceLocal {

    Metric metric = new Metric();
    
    @Inject
    MetricDAO metricDAO;

    @Override
    public void addMetric(Date date, String value, Long deviceId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Metric> getMetricsByDevice(Long deviceId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
