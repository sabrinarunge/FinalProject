package models;

import java.io.*;
public class OpeningHours
{
    private Boolean openNow;
    private String[] weekdayText;
    private Boolean permanentlyClosed;

    public OpeningHours(Boolean openNow, String[] weekdayText, Boolean permanentlyClosed)
    {
        this.openNow = openNow;
        this.weekdayText = weekdayText;
        this.permanentlyClosed = permanentlyClosed;
    }
}
