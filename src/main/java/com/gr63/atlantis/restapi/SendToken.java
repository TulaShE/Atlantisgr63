/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.restapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gr63.atlantis.business.domain.Device;
import com.gr63.atlantis.business.domain.User;
import com.gr63.atlantis.model.DeviceBean;
import com.gr63.atlantis.model.UserBean;
import java.io.StringReader;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 *
 * @author root
 */
@Stateless
@Path("sendtoken")
public class SendToken {

    @Context
    private UriInfo context;
    
    @Inject
    UserBean userBean;
    
    @Inject
    DeviceBean deviceBean;

    /**
     * Creates a new instance of GenericResource
     */
    public SendToken() {
    }
        
    @GET
    public String hello_test() {
        return "Hello";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(String body) {
        
        body = body.replaceAll("\r?\n", "");

        Map<String, String> map = new Gson().fromJson(body, new TypeToken<Map<String, String>>(){}.getType());
        
        String token = map.get("token");
        
        String[] tokenInterestingPart = token.split("\\.");
   
        
        if (tokenInterestingPart.length != 3)
        {
            return Response.status(Status.BAD_REQUEST).build();
        }
        byte[] tokenBytes = Base64.getDecoder().decode(tokenInterestingPart[1]);
        String decodedToken = new String(tokenBytes);
        
        
        JsonReader reader2 = Json.createReader(new StringReader(decodedToken));
        JsonObject userObject = reader2.readObject();
        reader2.close();
        
        String guid = userObject.getString("oid");
        
        userBean.setGuid(guid);
        userBean.setFirstname(userObject.getString("given_name"));
        userBean.setLastname(userObject.getString("family_name"));
        
        userBean.create();
        
        User userTmp;
        userTmp = userBean.authentificationByGuid(guid);
        
        if (userTmp == null)
        {
            return Response.status(Status.NOT_FOUND).build();
        }
                
        String json;
        json = "{\"user\": {"
                + "\"id\":\""+userTmp.getId()+"\","
                + "\"name\":\""+userTmp.getFirstname()+" "+userTmp.getLastname()+"\","
                + "\"isAdmin\":\""+userTmp.isAdmin()+"\"}}";
        
        return Response.ok().entity(json).build();
    }
    
    @GET
    @Path("/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendToken_getDevices(@PathParam("token") String token) {
        String[] tokenInterestingPart = token.split("\\.");
        if (tokenInterestingPart.length != 3)
        {
            return Response.status(Status.BAD_REQUEST).build();
        }
        
        byte[] tokenBytes = Base64.getDecoder().decode(tokenInterestingPart[1]);
        String decodedToken = new String(tokenBytes);
        
                
        JsonReader reader2 = Json.createReader(new StringReader(decodedToken));
        JsonObject userObject = reader2.readObject();
        reader2.close();
        
        String guid = userObject.getString("oid");
        
        System.out.println("Jeremzer guid: "+guid);
        User userTmp;
        
        try
        {
            userTmp = userBean.authentificationByGuid(guid);
        }
        catch(javax.ejb.EJBTransactionRolledbackException e)
        {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        if (userTmp == null)
        {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        List<Device> devices = deviceBean.getListDevicesOfUser(Long.valueOf(userTmp.getId()));
        
        String devicesString;
        devicesString = "\"devices\": [";
       
        for (int i = 0; i < devices.size(); i++) {
            Device device = (Device) devices.get(i);
            devicesString += "{\"id\":"+device.getId()+",";
            devicesString += "\"mac_address\":\""+device.getMacAddress()+"\",";
            devicesString += "\"name\":\""+device.getName()+"\",";
            devicesString += "\"deviceType\":\""+device.getDeviceType().getId()+"\"},"; 
        }
        // remove the last ','
        devicesString = devicesString.substring(0, devicesString.length() - 1);
        devicesString += "]";
        
        
        String json = new String();
        json = "{\"user\": {"
                + "\"id\":\""+userTmp.getId()+"\","
                + "\"name\":\""+userTmp.getFirstname()+" "+userTmp.getLastname()+"\","
                + "\"isAdmin\":\""+userTmp.isAdmin()+"\"},";
        
        json += devicesString;
        json += "}";
        
        return Response.ok().entity(json).build();
    }
}
