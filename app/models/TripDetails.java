package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class TripDetails
{
    @Id
    private int reqTripId;
    private int userId;
    private String destinationName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfTravelers;
    private Boolean pets;
    private BigDecimal budget;
    private String tripPurpose;
    private String nonNegotiable;
    private String notes;

    public TripDetails(int userId, int reqTripId, String destinationName, LocalDate startDate, LocalDate endDate, int numberOfTravelers, Boolean pets, BigDecimal budget, String tripPurpose, String nonNegotiable, String notes)
    {
        this.userId = userId;
        this.reqTripId = reqTripId;
        this.destinationName = destinationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfTravelers = numberOfTravelers;
        this.pets = pets;
        this.budget = budget;
        this.tripPurpose = tripPurpose;
        this.nonNegotiable = nonNegotiable;
        this.notes = notes;
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

    public int getNumberOfTravelers()
    {
        return numberOfTravelers;
    }

    public void setNumberOfTravelers(int numberOfTravelers)
    {
        this.numberOfTravelers = numberOfTravelers;
    }

    public Boolean getPets()
    {
        return pets;
    }

    public void setPets(Boolean pets)
    {
        this.pets = pets;
    }

    public BigDecimal getBudget()
    {
        return budget;
    }

    public void setBudget(BigDecimal budget)
    {
        this.budget = budget;
    }

    public String getNonNegotiable()
    {
        return nonNegotiable;
    }

    public void setNonNegotiable(String nonNegotiable)
    {
        this.nonNegotiable = nonNegotiable;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }
}
