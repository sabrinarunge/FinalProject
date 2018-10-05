package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TripAccommodation
{
    @Id private int tripAccommodationId;
    private int accommodationId;
    private int tripId;
    private LocalDate startDate;
    private LocalDate endDate;

    public int getTripAccommodationId()
    {
        return tripAccommodationId;
    }

    public int getAccommodationId()
    {
        return accommodationId;
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
