/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.model;

import com.gr63.atlantis.business.domain.Device;
import com.gr63.atlantis.business.domain.User;
import com.gr63.atlantis.business.logic.DeviceServiceLocal;
import com.gr63.atlantis.business.logic.UserServiceLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
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
    private List<Device> listDevices;
    private List<User> listUsers;
    private List<Device> listDevicesOfUser;

    private Device selectDevice;
    private String errorMessage;

    @Inject
    DeviceServiceLocal deviceService;
    @Inject
    UserServiceLocal userService;

    private Device device;
    private User user;
    /**
     * Creates a new instance of DeviceBean
     */
    public DeviceBean() {
    }


    public String listDevices(){
        listDevices = deviceService.getAllDevices();
        return "devicesList";
    }

    public String detailDevice(){
        return "deviceDetails";
    }

    public String goToLinkDeviceToUser(){
        errorMessage = "";
        listUsers = userService.getAllUser();
        listDevices = deviceService.getAllDevices();
        return "linkDeviceToUser";
    }

    public String goToRemoveLinkDeviceToUser(){
        if(userId != null)
        {
            listDevicesOfUser = deviceService.getDeviceByUser(userId);
        }
        listUsers = userService.getAllUser();
        return "removeDeviceFromUser";
    }

    public String linkDeviceToUser(){
        try{
        deviceService.linkDeviceToUser(userId, deviceId);
        return "home";
        }
        catch(Exception e){
         errorMessage = "These user and device are already linked. Please change.";
        }
        return "linkDeviceToUser";
    }

    public String removeUserFromDevice(){
        deviceService.unlinkDeviceFromUser(userId, deviceId);
        return "home";
    }

    public String createDevice(){
        System.out.println("User creation");

        deviceService.save(name, macAddress, deviceTypeId);

        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();

        return "index";
    }

    public void changeOnUserUnlink(){
        listDevicesOfUser = deviceService.getDeviceByUser(userId);
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

    public List<Device> getListDevices() {
        return listDevices;
    }

    public void setListDevices(List<Device> listDevices) {
        this.listDevices = listDevices;
    }

    public Device getSelectDevice() {
        return selectDevice;
    }

    public void setSelectDevice(Device selectDevice) {
        this.selectDevice = selectDevice;
    }

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    public List<Device> getListDevicesOfUser(Long userId) {
        return deviceService.getDeviceByUser(userId);
    }

    public void setListDevicesOfUser(List<Device> listDevicesOfUser) {
        this.listDevicesOfUser = listDevicesOfUser;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }



}
