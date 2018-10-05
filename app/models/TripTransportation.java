package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TripTransportation
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int tripTransportationId;
    private int transportationId;
    private int tripId;
    private LocalDate startDate;
    private LocalDate endDate;

    public int getTripTransportationId()
    {
        return tripTransportationId;
    }

    public int getTransportationId()
    {
        return transportationId;
    }

    public int getTripId()
    {
        return tripId;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }
}
