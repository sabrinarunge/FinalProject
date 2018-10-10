package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Activity
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int activityId;
    private String activityName;
    private String activityInformation;

    public int getActivityId()
    {
        return activityId;
    }

    public void setActivityId(int activityId)
    {
        this.activityId = activityId;
    }

    public String getActivityName()
    {
        return activityName;
    }

    public void setActivityName(String activityName)
    {
        this.activityName = activityName;
    }

    public String getActivityInformation()
    {
        return activityInformation;
    }

    public void setActivityInformation(String activityInformation)
    {
        this.activityInformation = activityInformation;
    }
}
