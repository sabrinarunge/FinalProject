package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dining
{
    @Id private int diningId;
    private String diningName;
    private int destinationId;
    private int diningTypeId;
    private String diningDescription;

    public int getDiningId()
    {
        return diningId;
    }

    public String getDiningName()
    {
        return diningName;
    }

    public int getDestinationId()
    {
        return destinationId;
    }

    public int getDiningTypeId()
    {
        return diningTypeId;
    }

    public String getDiningDescription()
    {
        return diningDescription;
    }
}
