package de.codefest8.gamification8.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import de.codefest8.gamification8.UserMessagesHandler;

public class AchievementDTO {
    private static final String LOG_TAG = "AchievementDTO";

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_POINTS = "points";
    private static final String FIELD_USER = "user";
    private static final String FIELD_TRIP = "trip";

    private long id;
    private String name;
    private int points;
    private UserDTO user;
    private TripDTO trip;
    public AchievementDTO() {
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public TripDTO getTrip() {
        return trip;
    }
    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public static AchievementDTO fromJson(JSONObject object, UserDTO user) {
        AchievementDTO achievement = new AchievementDTO();
        try {
            achievement.setId(object.getLong(FIELD_ID));
            achievement.setName(object.getString(FIELD_NAME));
            achievement.setPoints(object.getInt(FIELD_POINTS));
            achievement.setUser(user);

        } catch (JSONException ex) {
            UserMessagesHandler.getInstance().registerError("Error while parsing UserDTO JSON.");
            Log.e(LOG_TAG, ex.toString());
        }

        return achievement;
    }
}
