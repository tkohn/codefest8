package de.codefest8.gamification8.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import de.codefest8.gamification8.UserMessagesHandler;

public class TripDTO {
    private static final String LOG_TAG = "TriDTO";

    private static final String FIELD_ID = "id";
    private static final String FIELD_STARTTIME = "startTime";
    private static final String FIELD_ROUTELENGTH = "routeLength";

    private long id;
    private long user;
    private Timestamp startTime;
    private double routeLength;

    public TripDTO(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public String getStartTimeString()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd. MMMM yyyy");
        return sdf.format(this.getStartTime());
    }

    public String getStartDateTimeString() {
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm dd. MMMM yyyy");
        return sdf.format(this.getStartTime());
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public double getRouteLength() {
        return routeLength;
    }

    public String getRouteLengthKMString()
    {
        return Double.toString(Math.ceil(routeLength / 10)/100);
    }

    public void setRouteLength(double routeLength) {
        this.routeLength = routeLength;
    }

    public static TripDTO fromJson(JSONObject object) {
        TripDTO trip = new TripDTO();
        try {
            trip.setId(object.getLong(FIELD_ID));
            trip.setStartTime(new Timestamp(object.getLong(FIELD_STARTTIME)));
            trip.setRouteLength(object.getDouble(FIELD_ROUTELENGTH));
        } catch (JSONException ex) {
            UserMessagesHandler.getInstance().registerError("Error while parsing UserDTO JSON.");
            Log.e(LOG_TAG, ex.toString());
        }

        return trip;
    }
}
