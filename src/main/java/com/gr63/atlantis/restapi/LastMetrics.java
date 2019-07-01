/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.restapi;

import com.gr63.atlantis.business.domain.Metric;
import com.gr63.atlantis.model.MetricBean;
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
@Path("lastmetrics")
public class LastMetrics {
    
    @Context
    private UriInfo context;
    
    
    @Inject
    MetricBean metricBean;

    /**
     * Creates a new instance of GenericResource
     */
    public LastMetrics() {
    }
    
    
    @GET
    @Path("/{deviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastMetrics(@PathParam("deviceId") String deviceId)
    {
        List<Metric> metrics = metricBean.getAllMetrics(Long.parseLong(deviceId));
        
        String metricsString;
        metricsString = "[";
       
        for (int i = 0; i < metrics.size(); i++) {
            Metric metric = (Metric) metrics.get(i);
            metricsString += "{\"id\":"+metric.getId()+",";
            metricsString += "\"date\":"+metric.getDate().getTime()+",";
            metricsString += "\"value\":\""+metric.getValue()+"\"},";
        }
        metricsString = metricsString.substring(0, metricsString.length() - 1);
        metricsString += "]";
        
        return Response.ok().entity(metricsString)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }
}
