/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.model;

import com.gr63.atlantis.business.domain.Device;
import com.gr63.atlantis.business.logic.DeviceServiceLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dev
 */
@Named(value = "deviceModel")
@SessionScoped
public class DeviceBean implements Serializable {

    private String macAddress, name;
    private Long deviceTypeId, deviceId, userId;
    
    @Inject
    DeviceServiceLocal deviceService;
    
    private Device device;
    /**
     * Creates a new instance of DeviceBean
     */
    public DeviceBean() {
    }
    
    public String listDevices(){
        return "devicesList";
    }
    
    public String detailDevice(){
        return "deviceDetails";
    }
    
    public String linkDeviceToUser(){
        deviceService.linkDeviceToUser(userId, deviceId);
        return "index";
    }
    
    public String removeUserFromDevice(){
        return "removeDeviceFromUser";
    }
    
    public String createDevice(){
        System.out.println("User creation");
        
        deviceService.save(name, macAddress, deviceTypeId);
        
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        
        return "index";
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Long getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceTypeId = deviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
