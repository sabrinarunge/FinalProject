package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Trip
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int tripId;
    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfTravelers;
    private String pets;
    private BigDecimal budget;
    private String tripDescription;
    private String nonNegotiable;

    public int getTripId()
    {
        return tripId;
    }

    public int getUserId()
    {
        return userId;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public int getNumberOfTravelers()
    {
        return numberOfTravelers;
    }

    public String getPets()
    {
        return pets;
    }

    public BigDecimal getBudget()
    {
        return budget;
    }

    public String getTripDescription()
    {
        return tripDescription;
    }

    public void setTripId(int tripId)
    {
        this.tripId = tripId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public void setNumberOfTravelers(int numberOfTravelers)
    {
        this.numberOfTravelers = numberOfTravelers;
    }

    public void setPets(String pets)
    {
        this.pets = pets;
    }

    public void setBudget(BigDecimal budget)
    {
        this.budget = budget;
    }

    public void setTripDescription(String tripDescription)
    {
        this.tripDescription = tripDescription;
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
