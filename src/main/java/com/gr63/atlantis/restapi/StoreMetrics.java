/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.restapi;

import com.gr63.atlantis.model.MetricBean;
import java.io.StringReader;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author root
 */
@Path("/storemetrics")
@Consumes(MediaType.APPLICATION_JSON)
public class StoreMetrics {
    
        
    @Context
    private UriInfo context;
    
    @Inject 
    private MetricBean metricBean;
    
    public StoreMetrics() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeMetrics(String json)
    {
        JsonReader reader2 = Json.createReader(new StringReader(json));
        JsonObject userObject = reader2.readObject();
        reader2.close();
        
        String value = userObject.getString("Value");
        
        
        return Response.ok().build();
    }
}
