/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.integration;

import com.gr63.atlantis.business.domain.Device;
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
public class DeviceDAO {

    @PersistenceContext(unitName="atlantisPU")
    private EntityManager em;
    
    public void insert(Device device){
        em.persist(device);
    }
    
    public List<Device> getDevices(){
        Query query = em.createQuery("SELECT d FROM Device d");
        
        List<Device> devices = query.getResultList();
        
        return devices;
    }
    
    public List<Device> getUserDevices(Long userId){
                
        Query query = em.createNativeQuery("SELECT * FROM device WHERE device.id IN (SELECT userhasdevice.id_device FROM userhasdevice WHERE userhasdevice.id_user='"+userId+"');", Device.class);
        List<Device> devices = query.getResultList();
        return devices;
    }
    
    public Device getDeviceById(Long deviceId){
        Device device = em.find(Device.class, deviceId);
        
        return device;
    }
    
    public Device updateDevice(Device device){
        em.merge(device);
        
        return device;
    }
}