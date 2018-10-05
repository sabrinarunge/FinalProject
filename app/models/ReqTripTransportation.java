package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReqTripTransportation
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int reqTripTransportationId;
    private int reqTripId;
    private int transportationTypeId;
    private int localTransportationTypeId;

    public int getReqTripTransportationId()
    {
        return reqTripTransportationId;
    }

    public void setReqTripTransportationId(int reqTripTransportationId)
    {
        this.reqTripTransportationId = reqTripTransportationId;
    }

    public int getReqTripId()
    {
        return reqTripId;
    }

    public void setReqTripId(int reqTripId)
    {
        this.reqTripId = reqTripId;
    }

    public int getTransportationTypeId()
    {
        return transportationTypeId;
    }

    public void setTransportationTypeId(int transportationTypeId)
    {
        this.transportationTypeId = transportationTypeId;
    }

    public int getLocalTransportationTypeId()
    {
        return localTransportationTypeId;
    }

    public void setLocalTransportationTypeId(int localTransportationTypeId)
    {
        this.localTransportationTypeId = localTransportationTypeId;
    }
}
