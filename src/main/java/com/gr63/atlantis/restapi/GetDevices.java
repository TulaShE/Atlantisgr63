/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.restapi;

import com.gr63.atlantis.business.domain.Device;
import com.gr63.atlantis.model.DeviceBean;
import com.gr63.atlantis.model.UserBean;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author root
 */
@Stateless
@Path("/getdevices")
public class GetDevices {

    @Context
    private UriInfo context;
    
    @Inject
    UserBean userBean;
    
    public GetDevices(){
    }
    
    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDevices(@PathParam("userId") String userId)
    {
        System.out.println(userId);
        
        List <Device> devices;
        
        try{
            devices = userBean.getDevices(Long.valueOf(userId));
        }
        catch(Exception e)
        {
            System.out.println(userBean);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        
        if (devices.size()==0)
        {
                    return Response.ok()
                .entity("[]")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
        }
        
        System.out.println("bono");
        
        String devicesString;
        devicesString = "[";
       
        for (int i = 0; i < devices.size(); i++) {
            Device device = (Device) devices.get(i);
            devicesString += "{\"id\":"+device.getId()+",";
            devicesString += "\"mac_address\":\""+device.getMacAddress()+"\",";
            devicesString += "\"name\":\""+device.getName()+"\",";
            devicesString += "\"deviceType\":\""+device.getDeviceType().getName()+"\"},"; 
        }
        devicesString = devicesString.substring(0, devicesString.length() - 1);
        devicesString += "]";
        
        return Response.ok()
                .entity(devicesString)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }
}
