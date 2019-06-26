/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.domain;

import java.util.List;

/**
 *
 * @author dev
 */
public class Device {
    
    private Long id;
    private String name;
    private String mac_address;
    private Long typeId;
    private List<User> deviceUsers;
    private List<Metric> deviceMetrics;

    public Long getId(){
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public List<User> getDeviceUsers() {
        return deviceUsers;
    }

    public void setDeviceUsers(List<User> deviceUsers) {
        this.deviceUsers = deviceUsers;
    }

    public List<Metric> getDeviceMetrics() {
        return deviceMetrics;
    }

    public void setDeviceMetrics(List<Metric> deviceMetrics) {
        this.deviceMetrics = deviceMetrics;
    }    
}
