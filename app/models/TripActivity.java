package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TripActivity
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int tripActivityId;
    private int activityId;
    private int reqTripId;

    public int getTripActivityId()
    {
        return tripActivityId;
    }

    public int getActivityId()
    {
        return activityId;
    }

    public int getReqTripId()
    {
        return reqTripId;
    }

    public void setTripActivityId(int tripActivityId)
    {
        this.tripActivityId = tripActivityId;
    }

    public void setActivityId(int activityId)
    {
        this.activityId = activityId;
    }

    public void setReqTripId(int reqTripId)
    {
        this.reqTripId = reqTripId;
    }
}
