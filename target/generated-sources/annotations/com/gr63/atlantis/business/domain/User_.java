package com.gr63.atlantis.business.domain;

import com.gr63.atlantis.business.domain.Device;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-27T14:53:52")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstname;
    public static volatile ListAttribute<User, Device> userDevices;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, Boolean> isAdmin;
    public static volatile SingularAttribute<User, String> lastname;

}