package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TripTransportation
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int tripTransportationId;
    private int transportationId;
    private int reqTripId;

    public int getTripTransportationId()
    {
        return tripTransportationId;
    }

    public int getTransportationId()
    {
        return transportationId;
    }

    public int getReqTripId()
    {
        return reqTripId;
    }

    public void setTripTransportationId(int tripTransportationId)
    {
        this.tripTransportationId = tripTransportationId;
    }

    public void setTransportationId(int transportationId)
    {
        this.transportationId = transportationId;
    }

    public void setReqTripId(int reqTripId)
    {
        this.reqTripId = reqTripId;
    }
}
