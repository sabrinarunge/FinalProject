package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserRestriction
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int userRestrictionId;
    private int userId;
    private int restrictionId;

    public int getUserRestrictionId()
    {
        return userRestrictionId;
    }

    public int getUserId()
    {
        return userId;
    }

    public int getRestrictionId()
    {
        return restrictionId;
    }

    public void setUserRestrictionId(int userRestrictionId)
    {
        this.userRestrictionId = userRestrictionId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public void setRestrictionId(int restrictionId)
    {
        this.restrictionId = restrictionId;
    }
}
