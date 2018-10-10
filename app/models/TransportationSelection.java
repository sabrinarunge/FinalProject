package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TransportationSelection
{
    @Id private String transportationName;
    private String transportationInformation;

    public TransportationSelection(String transportationName, String transportationInformation)
    {
        this.transportationName = transportationName;
        this.transportationInformation = transportationInformation;
    }

    public String getTransportationName()
    {
        return transportationName;
    }

    public String getTransportationInformation()
    {
        return transportationInformation;
    }
}
