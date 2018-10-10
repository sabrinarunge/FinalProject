package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TripDining
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int tripDiningId;
    private int diningId;
    private int reqTripId;

    public int getTripDiningId()
    {
        return tripDiningId;
    }

    public int getDiningId()
    {
        return diningId;
    }

    public int getReqTripId()
    {
        return reqTripId;
    }

    public void setTripDiningId(int tripDiningId)
    {
        this.tripDiningId = tripDiningId;
    }

    public void setDiningId(int diningId)
    {
        this.diningId = diningId;
    }

    public void setReqTripId(int reqTripId)
    {
        this.reqTripId = reqTripId;
    }
}
