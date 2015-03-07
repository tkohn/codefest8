package de.codefest8.gamification8.models;

/**
 * Created by koerfer on 07.03.2015.
 */
public class Track {

    private String DateTime;

    public Track(String dateTime)
    {
        this.DateTime = dateTime;
    }

    public String GetDateTime()
    {
        return this.DateTime;
    }
}
