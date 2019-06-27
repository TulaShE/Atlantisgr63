/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.logic;

import com.gr63.atlantis.business.domain.User;
import com.gr63.atlantis.integration.UserDAO;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author dev
 */
@Stateless
public class UserService implements UserServiceLocal {

    private User user = new User();
    
    @Inject
    UserDAO userDAO;

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
        System.out.println("Sauvegarde du user");
    }

    @Override
    public void getUserById(Long userId) {
        userDAO.getUserById(userId);
    }
    
    @Override
    public User authentification(String guid)
    {
        return userDAO.authentification(guid);
    }

    
}
