/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.restapi;

import com.gr63.atlantis.model.UserBean;
import java.util.Base64;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
public class GenericResource {

    @Context
    private UriInfo context;
    
    @Inject
    UserBean userBean;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
        
    @GET
    
    public String hello_test() {
        return "Hello";
    }
    
    
    @GET
    @Path("/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDevices(@PathParam("token") String token) {
        byte[] tokenBytes = Base64.getDecoder().decode(token);
        String decodedToken = new String(tokenBytes);
        //JSONObject json = new JSONObject(decodedToken);
        //System.out.println(json.get("title"));
        boolean isAuthentified = this.userBean.authentification(decodedToken);
        //boolean isAuthentified = true;
        if (!isAuthentified)
        {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok().build();
    }
    
  /*  
  curl --header "Content-Type: application/json" \
  --request GET \
  --data '{"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ilg1ZVhrNHh5b2pORnVtMWtsMll0djhkbE5QNC1jNTdkTzZRR1RWQndhTmsifQ"}' \
  http://localhost:3000/api/login
*/
    /**
     * Retrieves representation of an instance of com.gr63.atlantis.restapi.GenericResource
     * @return an instance of java.lang.String
     */
    /*
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }*/

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
