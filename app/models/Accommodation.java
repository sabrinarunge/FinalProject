package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Accommodation
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int accommodationId;
    private String accommodationName;
    private String accommodationInformation;

    public int getAccommodationId()
    {
        return accommodationId;
    }

    public void setAccommodationId(int accommodationId)
    {
        this.accommodationId = accommodationId;
    }

    public String getAccommodationName()
    {
        return accommodationName;
    }

    public void setAccommodationName(String accommodationName)
    {
        this.accommodationName = accommodationName;
    }

    public String getAccommodationInformation()
    {
        return accommodationInformation;
    }

    public void setAccommodationInformation(String accommodationInformation)
    {
        this.accommodationInformation = accommodationInformation;
    }
}
