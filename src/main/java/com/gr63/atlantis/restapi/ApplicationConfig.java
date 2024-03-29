/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.restapi;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author root
 */
@javax.ws.rs.ApplicationPath("mobile")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.gr63.atlantis.restapi.ComplexMetrics.class);
        resources.add(com.gr63.atlantis.restapi.GetDevices.class);
        resources.add(com.gr63.atlantis.restapi.LastMetrics.class);
        resources.add(com.gr63.atlantis.restapi.SendOrder.class);
        resources.add(com.gr63.atlantis.restapi.SendToken.class);
    }
    
}