/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.model;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author dev
 */
@Named(value = "deviceModel")
@SessionScoped
public class DeviceBean implements Serializable {

    private String macAddress;
    private Long deviceType;
    
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
        return "linkDeviceToUser";
    }
    
    public String removeUserFromDevice(){
        return "removeDeviceFromUser";
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }
    
}
