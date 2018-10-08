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
    private int accommodationTypeId;
    private int destinationId;
    private String accommodationDescription;
    private BigDecimal rating;
    private String URL;
    private String address;
    private String phoneNumber;
    private String priceLevel;

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

    public BigDecimal getRating()
    {
        return rating;
    }

    public String getURL()
    {
        return URL;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getPriceLevel()
    {
        return priceLevel;
    }

    public void setAccommodationId(int accommodationId)
    {
        this.accommodationId = accommodationId;
    }

    public void setAccommodationName(String accommodationName)
    {
        this.accommodationName = accommodationName;
    }

    public void setAccommodationTypeId(int accommodationTypeId)
    {
        this.accommodationTypeId = accommodationTypeId;
    }

    public void setDestinationId(int destinationId)
    {
        this.destinationId = destinationId;
    }

    public void setAccommodationDescription(String accommodationDescription)
    {
        this.accommodationDescription = accommodationDescription;
    }

    public void setRating(BigDecimal rating)
    {
        this.rating = rating;
    }

    public void setURL(String URL)
    {
        this.URL = URL;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setPriceLevel(String priceLevel)
    {
        this.priceLevel = priceLevel;
    }
}
