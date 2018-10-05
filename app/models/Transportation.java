package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transportation
{
    @Id private int transportationId;
    private String transportationName;
    private int transportationTypeId;
    private String transportationDescription;

    public int getTransportationId()
    {
        return transportationId;
    }

    public String getTransportationName()
    {
        return transportationName;
    }

    public int getTransportationTypeId()
    {
        return transportationTypeId;
    }

    public String getTransportationDescription()
    {
        return transportationDescription;
    }
}
