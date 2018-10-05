package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DiningType
{
    @Id private int diningTypeId;
    private String diningTypeName;

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
