/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.logic;

import com.gr63.atlantis.business.domain.Device;
import com.gr63.atlantis.business.domain.Metric;
import com.gr63.atlantis.business.domain.User;
import com.gr63.atlantis.integration.DeviceDAO;
import com.gr63.atlantis.integration.MetricDAO;
import com.gr63.atlantis.integration.UserDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author dev
 */
@Stateful
public class UserService implements UserServiceLocal {

    private User user = new User();
    private Metric metric = new Metric();
    private Device device = new Device();
    private List<User> listUsers;
    
    @Inject
    UserDAO userDAO;
    
    @Inject
    MetricDAO metricDAO;
    
    @Inject
    DeviceDAO deviceDAO;

    @Override
    public void authentication(String firstname, String lastname) {
        //check if user exists and is admin
        System.out.println(firstname + " " + lastname);
    }

    @Override
    @Remove
    public void save(String firstname, String lastname, boolean isAdmin) {
        user.setFirstname(firstname);
        user.setLastname(lastname);
        userDAO.insert(user);
        
        DateFormat df = new SimpleDateFormat("dd/MM:yy HH:mm:ss");
        Date dateMetric = new Date();
        System.out.println(df.format(dateMetric));
        
        Long deviceLongId = new Long(1);
        device = deviceDAO.getDeviceById(deviceLongId);        
        
        metric.setDate(dateMetric);
        metric.setDeviceMetric(device);
        metric.setValue("50");
        metricDAO.insert(metric);
        System.out.println("Sauvegarde du user");
    }

    @Override
    public void getUserById(Long userId) {
        userDAO.getUserById(userId);
    }

    @Override
    public List<User> getAllUser() {
        listUsers = userDAO.getAllUsers();
        
        return listUsers;
    }

    
}
