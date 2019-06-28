/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.logic;

import com.gr63.atlantis.business.domain.Device;
import com.gr63.atlantis.business.domain.DeviceType;
import com.gr63.atlantis.business.domain.User;
import com.gr63.atlantis.integration.DeviceDAO;
import com.gr63.atlantis.integration.UserDAO;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;


/**
 *
 * @author dev
 */
@Stateful
public class DeviceService implements DeviceServiceLocal {

    private Device device = new Device();
    private DeviceType deviceType = new DeviceType();
    private List<Device> listDevices;
    
    @Inject
    DeviceDAO deviceDAO;
    
    @Inject
    UserDAO userDAO;
    
    @Override
    public void addDevice(String name, String macAddres, Long deviceType) {
        device.setName(name);
        device.setMacAddress(macAddres);
        
        deviceDAO.insert(device);
    }

    @Override
    @Remove
    public void save(String name, String macAddress, Long deviceTypeId) {
        device.setName(name);
        device.setMacAddress(macAddress);
        deviceType.setId(deviceTypeId);
        device.setDeviceType(deviceType);
        deviceDAO.insert(device);
        System.out.println("Sauvegarde du user");
    }

    @Override
    public List<Device> getAllDevices() {
        listDevices = deviceDAO.getDevices();
         return listDevices;
    }

    @Override
    public List<Device> getDeviceByUser(Long userId) {
    
        User u = userDAO.getUserById(userId);
        
        List<Device> userDevices = u.getUserDevices();
        
        return userDevices;
    }

    @Override
    public void getDeviceById(Long deviceId) {
        deviceDAO.getDeviceById(deviceId);
    }

    

    @Override
    public void unlinkDeviceFromUser(Long userId, Long deviceId) {
        
        Device d;
        d = deviceDAO.getDeviceById(deviceId);
        
        User u = userDAO.getUserById(userId);
        
        d.getDeviceUsers().remove(u);
        u.getUserDevices().remove(d);
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Device updateDevice(Device deviceToUpdate){
        device = deviceDAO.updateDevice(deviceToUpdate);
        
        return device;
    }

    @Override
    public Device linkDeviceToUser(Long userId, Long deviceId) {
        Device d;
        d = deviceDAO.getDeviceById(deviceId);
        
        User u = userDAO.getUserById(userId);
        
        d.getDeviceUsers().add(u);
        u.getUserDevices().add(d);
        
        return d;
    }
}
