package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserActivity
{
    @Id private String activityTypeName;

    public UserActivity(String activityTypeName)
    {
        this.activityTypeName = activityTypeName;
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
