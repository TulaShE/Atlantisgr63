/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gr63.atlantis.business.domain;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author dev
 */
@Entity
@Table(name="user")
public class User {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="first_name")
    private String firstname;
    @Column(name="last_name")
    private String lastname;
    @Column(name="is_admin")
    private boolean isAdmin;
    @Column(name="guid")
    private String guid;
    @Column(name="password")
    private int password;
    
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "userhasdevice",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_device")
    )
    private List<Device> userDevices;
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    
    public String getFirstname(){
        return firstname;
    }
    
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    
    public String getLastname(){
        return lastname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    public String getGuid()
    {
        return this.guid;
    }
    
    public void setGuid(String guid)
    {
        this.guid = guid;
    }

    public List<Device> getUserDevices() {
        return userDevices;
    }

    public void setUserDevices(List<Device> userDevices) {
        this.userDevices = userDevices;
    }    

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
    
    
}
