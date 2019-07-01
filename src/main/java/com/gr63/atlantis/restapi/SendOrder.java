/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.restapi;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author root
 */
@Stateless
@Path("sendorder")
public class SendOrder {
    public SendOrder() {
        
    }
    
    
    @Context
    private UriInfo context;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getComplexData(String body)
    {
        Client client = Client.create();
 
        WebResource webResource = client.resource("http://jenkins.posier.fr:5000/api/device/sendorder");
 
        
 
        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, body);
 
        if (response.getStatus() != 200) {
            System.out.println("Failed : HTTP error code : " + response.getStatus());
            
            String error= response.getEntity(String.class);
            System.out.println("Error: "+error);
            Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        String output = response.getEntity(String.class);
        
        return Response.ok().entity(output)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }
}
