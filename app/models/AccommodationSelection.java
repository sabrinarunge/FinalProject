package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccommodationSelection
{
    @Id private String accommodationName;
    private String accommodationInformation;

    public AccommodationSelection(String accommodationName, String accommodationInformation)
    {
        this.accommodationName = accommodationName;
        this.accommodationInformation = accommodationInformation;
    }

    public String getAccommodationName()
    {
        return accommodationName;
    }

    public String getAccommodationInformation()
    {
        return accommodationInformation;
    }
}
