package models;

import services.HashHelper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class User
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int userId;
    private String firstName;
    private String lastName;
    private String password;
    private String zipCode;
    private String emailAddress;
    private LocalDate birthDate;
    private String restriction;
    private byte[] photo;

    public int getUserId()
    {
        return userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPassword()
    {
        return password;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public LocalDate getBirthDate()
    {
        return birthDate;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setPassword(String password)
    {
        this.password = HashHelper.createPassword(password);
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public void setBirthDate(LocalDate birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getRestriction()
    {
        return restriction;
    }

    public void setRestriction(String restriction)
    {
        this.restriction = restriction;
    }

    public byte[] getPhoto()
    {
        return photo;
    }

    public void setPhoto(byte[] photo)
    {
        this.photo = photo;
    }
}
