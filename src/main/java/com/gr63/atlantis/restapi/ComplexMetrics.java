/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.restapi;

import com.gr63.atlantis.model.MetricBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author root
 */

@Stateless
@Path("getcomplexmetrics")
public class ComplexMetrics {
    
    
    @Context
    private UriInfo context;
    

    
    public ComplexMetrics()
    {
        
    }
    
    @GET
    @Path("/{deviceId}")
    public Response getComplexData(@PathParam("deviceId") String deviceId)
    {
        Client client = Client.create();
 
        WebResource webResource = client.resource("http://jenkins.posier.fr:5000/api/calculation/averagedaily/"+deviceId);
 
        
        Builder builder = webResource.accept(MediaType.APPLICATION_JSON);
 
        ClientResponse response = builder.get(ClientResponse.class);
 
        if (response.getStatus() != 200) {
            System.out.println("Failed : HTTP error code : " + response.getStatus());
            
            String error= response.getEntity(String.class);
            System.out.println("Error: "+error);
            Response.status(Status.BAD_REQUEST).build();
        }
        
        String output = response.getEntity(String.class);
        
        return Response.ok().entity(output)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }
}