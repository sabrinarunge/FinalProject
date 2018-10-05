package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserAccommodation
{
    @Id private int accommodationTypeId;
    private String accommodationTypeName;

    public UserAccommodation(int accommodationTypeId, String accommodationTypeName)
    {
        this.accommodationTypeId = accommodationTypeId;
        this.accommodationTypeName = accommodationTypeName;
    }

    public int getAccommodationTypeId()
    {
        return accommodationTypeId;
    }

    public void setAccommodationTypeId(int accommodationTypeId)
    {
        this.accommodationTypeId = accommodationTypeId;
    }

    public String getAccommodationTypeName()
    {
        return accommodationTypeName;
    }

    public void setAccommodationTypeName(String accommodationTypeName)
    {
        this.accommodationTypeName = accommodationTypeName;
    }
}
