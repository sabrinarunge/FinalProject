package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserReqTrip
{
    @Id private int reqTripId;
    private int userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String zipCode;
    private String restriction;

    public UserReqTrip(int reqTripId, int userId, String firstName, String lastName, String emailAddress, String zipCode, String restriction)
    {
        this.reqTripId = reqTripId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.zipCode = zipCode;
        this.restriction = restriction;
    }

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

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getRestriction()
    {
        return restriction;
    }

    public void setRestriction(String restriction)
    {
        this.restriction = restriction;
    }
}
