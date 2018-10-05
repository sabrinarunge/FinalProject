package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReqTripAccommodation
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int reqTripAccommodationId;
    private int reqTripId;
    private int accommodationTypeId;

    public int getReqTripAccommodationId()
    {
        return reqTripAccommodationId;
    }

    public void setReqTripAccommodationId(int reqTripAccommodationId)
    {
        this.reqTripAccommodationId = reqTripAccommodationId;
    }

    public int getReqTripId()
    {
        return reqTripId;
    }

    public void setReqTripId(int reqTripId)
    {
        this.reqTripId = reqTripId;
    }

    public int getAccommodationTypeId()
    {
        return accommodationTypeId;
    }

    public void setAccommodationTypeId(int accommodationTypeId)
    {
        this.accommodationTypeId = accommodationTypeId;
    }
}
