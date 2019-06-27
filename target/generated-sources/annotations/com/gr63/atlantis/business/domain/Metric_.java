package com.gr63.atlantis.business.domain;

import com.gr63.atlantis.business.domain.Device;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-27T14:53:52")
@StaticMetamodel(Metric.class)
public class Metric_ { 

    public static volatile SingularAttribute<Metric, Date> date;
    public static volatile SingularAttribute<Metric, Device> deviceMetric;
    public static volatile SingularAttribute<Metric, Long> id;
    public static volatile SingularAttribute<Metric, String> value;

}