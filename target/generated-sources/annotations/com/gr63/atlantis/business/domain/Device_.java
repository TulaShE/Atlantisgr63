package com.gr63.atlantis.business.domain;

import com.gr63.atlantis.business.domain.DeviceType;
import com.gr63.atlantis.business.domain.Metric;
import com.gr63.atlantis.business.domain.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-01T15:55:00")
@StaticMetamodel(Device.class)
public class Device_ {

    public static volatile SingularAttribute<Device, DeviceType> deviceType;
    public static volatile ListAttribute<Device, Metric> deviceMetrics;
    public static volatile SingularAttribute<Device, String> macAddress;
    public static volatile SingularAttribute<Device, String> name;
    public static volatile SingularAttribute<Device, Long> id;
    public static volatile ListAttribute<Device, User> deviceUsers;

}
