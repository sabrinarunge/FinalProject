package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dining
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int diningId;
    private String diningName;
    private String diningInformation;
    private int diningTypeId;

    public int getDiningId()
    {
        return diningId;
    }

    public void setDiningId(int diningId)
    {
        this.diningId = diningId;
    }

    public String getDiningName()
    {
        return diningName;
    }

    public void setDiningName(String diningName)
    {
        this.diningName = diningName;
    }

    public String getDiningInformation()
    {
        return diningInformation;
    }

    public void setDiningInformation(String diningInformation)
    {
        this.diningInformation = diningInformation;
    }

    public int getDiningTypeId()
    {
        return diningTypeId;
    }

    public void setDiningTypeId(int diningTypeId)
    {
        this.diningTypeId = diningTypeId;
    }
}
