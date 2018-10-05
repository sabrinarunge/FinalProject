package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserId
{
    @Id
    private Integer userId;

    public UserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getId()
    {
        return userId;
    }
}