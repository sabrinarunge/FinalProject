package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ActivitySelection
{
    @Id private String activityName;
    private String activityInformation;

    public ActivitySelection(String activityName, String activityInformation)
    {
        this.activityName = activityName;
        this.activityInformation = activityInformation;
    }

    public String getActivityName()
    {
        return activityName;
    }

    public String getActivityInformation()
    {
        return activityInformation;
    }
}
