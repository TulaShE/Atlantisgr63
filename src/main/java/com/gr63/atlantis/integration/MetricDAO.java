/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.integration;

import com.gr63.atlantis.business.domain.Metric;
import java.text.SimpleDateFormat;
import java.util.Date;
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
//        System.out.println("date: "+date);
//        System.out.println("value: "+value);
//        System.out.println("deviceId: "+deviceId);
//        Query query = em.createNativeQuery("INSERT INTO `metric` (`value`, `date`, `id_device`) VALUES ('"+value+"', '"+date+"', '"+deviceId+"');");
//        //query.getSingleResult();
//        query.executeUpdate();

//        Query query = em.createQuery("INSERT INTO metric (`value, `date`, `id_device`) VALUES (?, ?, ?)");
//        query.setParameter(1, value)
//                .setParameter(2, date)
//                .setParameter(3, deviceId);
//        
//        query.executeUpdate();

        
        em.persist(metric);
    }
    
    public List<Metric> getMetricsByDevice(Long deviceId){
        
        Query query = em.createNativeQuery("SELECT * FROM metric WHERE id_device='"+deviceId+"'", Metric.class);
        
        List<Metric> deviceMetrics = query.getResultList();
        
        return deviceMetrics;
    } 
}