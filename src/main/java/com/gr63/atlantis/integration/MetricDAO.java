/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.integration;

import com.gr63.atlantis.business.domain.Metric;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    public List<Metric> getMetricsByDevice(Long deviceId){
        
        Query query = em.createNativeQuery("SELECT * FROM metric WHERE id_device='"+deviceId+"'", Metric.class);
        
        List<Metric> deviceMetrics = query.getResultList();
        
        return deviceMetrics;
    } 
}