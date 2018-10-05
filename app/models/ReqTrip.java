package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ReqTrip
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int reqTripId;
    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfTravelers;
    private Boolean pets;
    private BigDecimal budget;
    private String tripPurpose;
    private String nonNegotiable;
    private String notes;

    public int getReqTripId()
    {
        return reqTripId;
    }

    public void setReqTripId(int reqTripId)
    {
        this.reqTripId = reqTripId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
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

    public void setPets(String pets)
    {
        if(pets.equals("yes"))
        {
            this.pets = true;
        }
        this.pets = false;
    }

    public BigDecimal getBudget()
    {
        return budget;
    }

    public void setBudget(BigDecimal budget)
    {
        this.budget = budget;
    }

    public String getTripPurpose()
    {
        return tripPurpose;
    }

    public void setTripPurpose(String tripPurpose)
    {
        this.tripPurpose = tripPurpose;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getNonNegotiable()
    {
        return nonNegotiable;
    }

    public void setNonNegotiable(String nonNegotiable)
    {
        this.nonNegotiable = nonNegotiable;
    }
}
