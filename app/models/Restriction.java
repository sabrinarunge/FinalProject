package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restriction
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int restrictionId;
    private String restrictionName;

    public int getRestrictionId()
    {
        return restrictionId;
    }

    public String getRestrictionName()
    {
        return restrictionName;
    }

    public void setRestrictionId(int restrictionId)
    {
        this.restrictionId = restrictionId;
    }

    public void setRestrictionName(String restrictionName)
    {
        this.restrictionName = restrictionName;
    }
}
