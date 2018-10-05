package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NonNegotiable
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int nonNegotiableId;
    private String nonNegotiableName;

    public int getNonNegotiableId()
    {
        return nonNegotiableId;
    }

    public String getNonNegotiableName()
    {
        return nonNegotiableName;
    }

    public void setNonNegotiableId(int nonNegotiableId)
    {
        this.nonNegotiableId = nonNegotiableId;
    }

    public void setNonNegotiableName(String nonNegotiableName)
    {
        this.nonNegotiableName = nonNegotiableName;
    }
}
