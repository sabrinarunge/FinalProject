package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TripDestination
{
    @Id private int tripDestinationId;
    private int destinationId;
    private int tripId;

    public int getTripDestinationId()
    {
        return tripDestinationId;
    }

    public int getDestinationId()
    {
        return destinationId;
    }

    public int getTripId()
    {
        return tripId;
    }
}
