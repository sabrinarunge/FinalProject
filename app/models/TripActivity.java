package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TripActivity
{
    @Id private int tripActivityId;
    private int activityId;
    private int tripId;

    public int getTripActivityId()
    {
        return tripActivityId;
    }

    public int getActivityId()
    {
        return activityId;
    }

    public int getTripId()
    {
        return tripId;
    }
}
