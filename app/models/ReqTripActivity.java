package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReqTripActivity
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int reqTripActivityId;
    private int reqTripId;
    private int activityTypeId;

    public int getReqTripActivityId()
    {
        return reqTripActivityId;
    }

    public void setReqTripActivityId(int reqTripActivityId)
    {
        this.reqTripActivityId = reqTripActivityId;
    }

    public int getReqTripId()
    {
        return reqTripId;
    }

    public void setReqTripId(int reqTripId)
    {
        this.reqTripId = reqTripId;
    }

    public int getActivityTypeId()
    {
        return activityTypeId;
    }

    public void setActivityTypeId(int activityTypeId)
    {
        this.activityTypeId = activityTypeId;
    }
}
