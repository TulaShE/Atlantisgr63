/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.integration;

import com.gr63.atlantis.business.domain.Metric;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author dev
 */
@Stateless
public class MetricDAO {

    @PersistenceContext(unitName="atlantisPU")
    private EntityManager em;
    
    public void insert(Metric metric){
        em.persist(metric);
    }
    
    public List<Metric> getMetricsByDeviceBetweenDates(Long deviceId, Date startDate){
        
        //DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        //String formattedDate = format.format(startDate);
        //System.out.println(formattedDate);
        
        Query query = em.createQuery("SELECT m FROM Metric m WHERE m.deviceMetric.id = :deviceId AND m.date > :startDate");
        query.setParameter("deviceId", deviceId);
        query.setParameter("startDate", startDate, TemporalType.DATE);
        
        List<Metric> deviceMetrics = query.getResultList();
        
        return deviceMetrics;
    }
    
    public List<Metric> getAllMetrics(){
        Query query = em.createQuery("SELECT m FROM Metric m");
        
        List<Metric> results = query.getResultList();
        
        return results;
    }
}
