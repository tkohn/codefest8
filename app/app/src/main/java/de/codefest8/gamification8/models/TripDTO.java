package de.codefest8.gamification8.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.codefest8.gamification8.UserMessagesHandler;

public class TripDTO {
    private static final String LOG_TAG = "TriDTO";

    private static final String FIELD_ID = "id";
    private static final String FIELD_USER = "user";
    private static final String FIELD_TRIP_DATA = "tripDataList";

    private long id;
    private UserDTO user;
    private List<TripDataDTO> tripDataList;
    public TripDTO() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public List<TripDataDTO> getTripDataList() {
        return tripDataList;
    }
    public void setTripDataList(List<TripDataDTO> tripDataList) {
        this.tripDataList = tripDataList;
    }

    public static TripDTO fromJson(JSONObject object) {
        TripDTO trip = new TripDTO();
        try {
            trip.setId(object.getLong(FIELD_ID));

        } catch (JSONException ex) {
            UserMessagesHandler.getInstance().registerError("Error while parsing UserDTO JSON.");
            Log.e(LOG_TAG, ex.toString());
        }

        return trip;
    }
}
