package de.codefest8.gamification8.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by koerfer on 07.03.2015.
 */
public class Track {

    private GregorianCalendar DateTime;

    public Track(GregorianCalendar dateTime)
    {
        this.DateTime = dateTime;

    }

    public String getDateTimeString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd. MMMM yyyy");
        sdf.setCalendar(this.DateTime);
        return sdf.format(this.DateTime.getTime());
    }
}
