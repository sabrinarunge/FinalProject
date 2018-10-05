package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDining
{
    @Id private int diningTypeId;
    private String diningTypeName;

    public UserDining(int diningTypeId, String diningTypeName)
    {
        this.diningTypeId = diningTypeId;
        this.diningTypeName = diningTypeName;
    }

    public int getDiningTypeId()
    {
        return diningTypeId;
    }

    public void setDiningTypeId(int diningTypeId)
    {
        this.diningTypeId = diningTypeId;
    }

    public String getDiningTypeName()
    {
        return diningTypeName;
    }

    public void setDiningTypeName(String diningTypeName)
    {
        this.diningTypeName = diningTypeName;
    }
}
