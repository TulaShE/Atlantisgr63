/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.logic;

import com.gr63.atlantis.business.domain.Metric;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dev
 */
@Local
public interface MetricServiceLocal {
    public void addMetric(Date date, String value, Long deviceId);
    
    public List<Metric> getMetricsByDevice(Long deviceId);
}
