package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DiningSelection
{
    @Id private String diningName;
    private String diningInformation;

    public DiningSelection(String diningName, String diningInformation)
    {
        this.diningName = diningName;
        this.diningInformation = diningInformation;
    }

    public String getDiningName()
    {
        return diningName;
    }

    public String getDiningInformation()
    {
        return diningInformation;
    }
}
