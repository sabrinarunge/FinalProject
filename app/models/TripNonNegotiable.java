package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TripNonNegotiable
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int tripNonNegotiableId;
    private int tripId;
    private int nonNegotiableId;

    public int getTripNonNegotiableId()
    {
        return tripNonNegotiableId;
    }

    public int getTripId()
    {
        return tripId;
    }

    public int getNonNegotiableId()
    {
        return nonNegotiableId;
    }

    public void setTripNonNegotiableId(int tripNonNegotiableId)
    {
        this.tripNonNegotiableId = tripNonNegotiableId;
    }

    public void setTripId(int tripId)
    {
        this.tripId = tripId;
    }

    public void setNonNegotiableId(int nonNegotiableId)
    {
        this.nonNegotiableId = nonNegotiableId;
    }
}
