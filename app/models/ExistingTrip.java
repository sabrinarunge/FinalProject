package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class ExistingTrip
{
    @Id private int userId;
    private int reqTripId;
    private String destinationName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String tripPurpose;

    public ExistingTrip(int userId, int reqTripId, String destinationName, LocalDate startDate, LocalDate endDate, String tripPurpose)
    {
        this.userId = userId;
        this.reqTripId = reqTripId;
        this.destinationName = destinationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tripPurpose = tripPurpose;
    }

    public int getUserId()
    {
        return userId;
    }

    public int getReqTripId()
    {
        return reqTripId;
    }

    public String getDestinationName()
    {
        return destinationName;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public void setReqTripId(int reqTripId)
    {
        this.reqTripId = reqTripId;
    }

    public void setDestinationName(String destinationName)
    {
        this.destinationName = destinationName;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public String getTripPurpose()
    {
        return tripPurpose;
    }

    public void setTripPurpose(String tripPurpose)
    {
        this.tripPurpose = tripPurpose;
    }
}
