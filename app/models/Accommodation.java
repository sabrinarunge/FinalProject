package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Accommodation
{
    @Id private int accommodationId;
    private String accommodationName;
    private int accommodationTypeId;
    private int destinationId;
    private String accommodationDescription;

    public int getAccommodationId()
    {
        return accommodationId;
    }

    public String getAccommodationName()
    {
        return accommodationName;
    }

    public int getAccommodationTypeId()
    {
        return accommodationTypeId;
    }

    public int getDestinationId()
    {
        return destinationId;
    }

    public String getAccommodationDescription()
    {
        return accommodationDescription;
    }
}
