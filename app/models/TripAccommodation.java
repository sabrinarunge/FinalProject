package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TripAccommodation
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int tripAccommodationId;
    private int accommodationId;
    private int reqTripId;


    public int getTripAccommodationId()
    {
        return tripAccommodationId;
    }

    public void setTripAccommodationId(int tripAccommodationId)
    {
        this.tripAccommodationId = tripAccommodationId;
    }

    public int getAccommodationId()
    {
        return accommodationId;
    }

    public void setAccommodationId(int accommodationId)
    {
        this.accommodationId = accommodationId;
    }

    public int getReqTripId()
    {
        return reqTripId;
    }

    public void setReqTripId(int reqTripId)
    {
        this.reqTripId = reqTripId;
    }
}
