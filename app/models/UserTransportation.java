package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserTransportation
{
    @Id private int transportationTypeId;
    private String transportationTypeName;

    public UserTransportation(int transportationTypeId, String transportationTypeName)
    {
        this.transportationTypeId = transportationTypeId;
        this.transportationTypeName = transportationTypeName;
    }

    public int getTransportationTypeId()
    {
        return transportationTypeId;
    }

    public void setTransportationTypeId(int transportationTypeId)
    {
        this.transportationTypeId = transportationTypeId;
    }

    public String getTransportationTypeName()
    {
        return transportationTypeName;
    }

    public void setTransportationTypeName(String transportationTypeName)
    {
        this.transportationTypeName = transportationTypeName;
    }
}
