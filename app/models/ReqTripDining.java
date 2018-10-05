package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReqTripDining
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int reqTripDiningId;
    private int reqTripId;
    private int diningTypeId;

    public int getReqTripDiningId()
    {
        return reqTripDiningId;
    }

    public void setReqTripDiningId(int reqTripDiningId)
    {
        this.reqTripDiningId = reqTripDiningId;
    }

    public int getReqTripId()
    {
        return reqTripId;
    }

    public void setReqTripId(int reqTripId)
    {
        this.reqTripId = reqTripId;
    }

    public int getDiningTypeId()
    {
        return diningTypeId;
    }

    public void setDiningTypeId(int diningTypeId)
    {
        this.diningTypeId = diningTypeId;
    }
}
