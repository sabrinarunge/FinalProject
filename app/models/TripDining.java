package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TripDining
{
    @Id private int tripDiningId;
    private int diningId;
    private int tripId;

    public int getTripDiningId()
    {
        return tripDiningId;
    }

    public int getDiningId()
    {
        return diningId;
    }

    public int getTripId()
    {
        return tripId;
    }
}
