package models;

import com.google.maps.model.Photo;
import com.google.maps.model.PlacesSearchResult;

import java.math.BigDecimal;

public class PlaceInfo
{

    private String name;
    private double lat;
    private double lng;
    private String placeId;
    private String type;
    private BigDecimal rating;
    private String url;
    private Photo[] photo;
    private String formattedAddress;
    private String formattedPhoneNumber;
    private String priceLevel;
    //OpeningHours openingHours;


    public PlaceInfo(String name, double lat, double lng)
    {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public void setPlaceId(String placeId)
    {
        this.placeId = placeId;
    }

    public String getPlaceId()
    {
        return placeId;
    }

    public Photo[] getPhoto()
    {
        return photo;
    }

    public void setPhoto(Photo[] photo)
    {
        this.photo = photo;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLng()
    {
        return lng;
    }

    public void setLng(double lng)
    {
        this.lng = lng;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public BigDecimal getRating()
    {
        return rating;
    }

    public void setRating(BigDecimal rating)
    {
        this.rating = rating;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getFormattedAddress()
    {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress)
    {
        this.formattedAddress = formattedAddress;
    }

    public String getFormattedPhoneNumber()
    {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber)
    {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public String getPriceLevel()
    {
        return priceLevel;
    }

    public void setPriceLevel(String priceLevel)
    {
        this.priceLevel = priceLevel;
    }






}
