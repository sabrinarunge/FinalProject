package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Destination
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int destinationId;
    private String destinationName;
    private String destinationDescription;

    public int getDestinationId()
    {
        return destinationId;
    }

    public String getDestinationName()
    {
        return destinationName;
    }

    public String getDestinationDescription()
    {
        return destinationDescription;
    }

    public void setDestinationId(int destinationId)
    {
        this.destinationId = destinationId;
    }

    public void setDestinationName(String destinationName)
    {
        this.destinationName = destinationName;
    }

    public void setDestinationDescription(String destinationDescription)
    {
        this.destinationDescription = destinationDescription;
    }
}
