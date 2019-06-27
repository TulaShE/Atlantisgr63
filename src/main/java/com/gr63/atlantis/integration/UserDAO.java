/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.integration;

import com.gr63.atlantis.business.domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author dev
 */
@Stateless
public class UserDAO {

    @PersistenceContext(unitName="atlantisPU")
    private EntityManager em;
    
    public void insert(User user){
        em.persist(user);
    }
    
    public User getUser(String firstname, String lastname){

        User user = new User();
        
        return user;
    }
    
    public List<User> getAllUsers(){
        
        List<User> Users = null;
        
        return Users;
    }
    
    public User getUserById(Long userId){
        User user = em.find(User.class, userId);
        
        return user;
    }
    
    public User authentification(String guid)
    {
        Query query = em.createQuery("SELECT m FROM User m WHERE m.guid = '" + guid + "'");
        
        List<User> users = query.getResultList();
        
        if (users.size() == 1)
        {
            return users.get(0);
        }
        else
        {
            return null;
        }
    }
}
