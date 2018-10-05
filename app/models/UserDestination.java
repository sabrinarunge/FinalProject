package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDestination
{
    @Id private int destinationId;
    private String destinationName;

    public UserDestination(int destinationId, String destinationName)
    {
        this.destinationId = destinationId;
        this.destinationName = destinationName;
    }

    public int getDestinationId()
    {
        return destinationId;
    }

    public void setDestinationId(int destinationId)
    {
        this.destinationId = destinationId;
    }

    public String getDestinationName()
    {
        return destinationName;
    }

    public void setDestinationName(String destinationName)
    {
        this.destinationName = destinationName;
    }
}
