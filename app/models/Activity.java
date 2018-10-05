package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Activity
{
    @Id private int activityId;
    private String activityName;
    private int destinationId;
    private int activityTypeId;
    private String activityDescription;

    public int getActivityId()
    {
        return activityId;
    }

    public String getActivityName()
    {
        return activityName;
    }

    public int getDestinationId()
    {
        return destinationId;
    }

    public int getActivityTypeId()
    {
        return activityTypeId;
    }

    public String getActivityDescription()
    {
        return activityDescription;
    }
}
