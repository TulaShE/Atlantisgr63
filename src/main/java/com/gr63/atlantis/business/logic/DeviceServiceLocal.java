/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.logic;

import com.gr63.atlantis.business.domain.Device;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dev
 */
@Local
public interface DeviceServiceLocal {
    public void addDevice(String macAddres, Long deviceType);
    public void save();
    
    public List<Device> getAllDevices();
    public List<Device> getDeviceByUser(Long userId);
    public Device getDeviceById(Long deviceId);
    
    public void linkDeviceToUser(Long userId, Long deviceId);
    public void unlinkDeviceFromUser(Long userId, Long deviceId);
}
