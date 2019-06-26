/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.logic;

import javax.ejb.Local;

/**
 *
 * @author dev
 */
@Local
public interface UserServiceLocal {
    public void authentication(String firstname, String lastname);
    public void save(String firstname, String lastname, boolean isAdmin);
}
