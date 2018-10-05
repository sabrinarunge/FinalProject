package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReqTripDestination
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int reqTripDestinationId;
    private int reqTripId;
    private int destinationId;

    public int getReqTripDestinationId()
    {
        return reqTripDestinationId;
    }

    public void setReqTripDestinationId(int reqTripDestinationId)
    {
        this.reqTripDestinationId = reqTripDestinationId;
    }

    public int getReqTripId()
    {
        return reqTripId;
    }

    public void setReqTripId(int reqTripId)
    {
        this.reqTripId = reqTripId;
    }

    public int getDestinationId()
    {
        return destinationId;
    }

    public void setDestinationId(int destinationId)
    {
        this.destinationId = destinationId;
    }
}
