/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.domain;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author dev
 */
@Entity
@Table(name="device")
public class Device {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Column(name = "mac_address")
    private String macAddress;
    
    @JoinColumn(name = "id_type_device")
    @ManyToOne
    private DeviceType deviceType;
    
    @ManyToMany(mappedBy = "userDevices")
    private List<User> deviceUsers;
    
    @OneToMany(mappedBy = "deviceMetric", cascade = CascadeType.MERGE)
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
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
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

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
}
