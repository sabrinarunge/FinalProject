package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ActivityType
{
    @Id private int activityTypeId;
    private String activityTypeName;

    public int getActivityTypeId()
    {
        return activityTypeId;
    }

    public void setActivityTypeId(int activityTypeId)
    {
        this.activityTypeId = activityTypeId;
    }

    public String getActivityTypeName()
    {
        return activityTypeName;
    }

    public void setActivityTypeName(String activityTypeName)
    {
        this.activityTypeName = activityTypeName;
    }
}
