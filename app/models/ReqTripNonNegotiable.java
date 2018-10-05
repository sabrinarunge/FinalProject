package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReqTripNonNegotiable
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int reqTripNonNegotiableId;
    private int nonNegotiableId;
    private int reqTripId;

    public int getReqTripNonNegotiableId()
    {
        return reqTripNonNegotiableId;
    }

    public void setReqTripNonNegotiableId(int reqTripNonNegotiableId)
    {
        this.reqTripNonNegotiableId = reqTripNonNegotiableId;
    }

    public int getNonNegotiableId()
    {
        return nonNegotiableId;
    }

    public void setNonNegotiableId(int nonNegotiableId)
    {
        this.nonNegotiableId = nonNegotiableId;
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
