package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transportation
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int transportationId;
    private String transportationName;
    private String transportationInformation;

    public int getTransportationId()
    {
        return transportationId;
    }

    public void setTransportationId(int transportationId)
    {
        this.transportationId = transportationId;
    }

    public String getTransportationName()
    {
        return transportationName;
    }

    public void setTransportationName(String transportationName)
    {
        this.transportationName = transportationName;
    }

    public String getTransportationInformation()
    {
        return transportationInformation;
    }

    public void setTransportationInformation(String transportationInformation)
    {
        this.transportationInformation = transportationInformation;
    }
}
