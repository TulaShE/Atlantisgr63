/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.logic;

import com.gr63.atlantis.business.domain.Device;
import com.gr63.atlantis.business.domain.Metric;
import com.gr63.atlantis.integration.MetricDAO;
import java.util.Date;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    @Remove
    public void addMetric(Date date, String value, Device device) {
        metric.setDate(date);
        metric.setValue(value);
        metric.setDeviceMetric(device);
        metricDAO.insert(metric);
        System.out.println("Sauvegarde de la metric");
    }

    @Override
    public List<Metric> getMetricsByDevice(Long deviceId) {
        List<Metric> deviceMetrics = metricDAO.getMetricsByDevice(deviceId);
        System.out.println("Metrics listed");
        return deviceMetrics;
    }
    
    
}
