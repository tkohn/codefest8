package de.codefest8.gamification8.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.codefest8.gamification8.UserMessagesHandler;

public class UserDTO {
    private static final String LOG_TAG = "UserDTO";

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_USER = "user";
    private static final String FIELD_FRIENDS = "friends";
    private static final String FIELD_TRIPS = "trips";
    private static final String FIELD_ACHIEVEMENTS = "achievements";

    private long id;
    private String name;
    private String password;
    private UserDTO user;
    private List<UserDTO> friends;
    private List<TripDTO> trips;
    private List<AchievementDTO> achievements;
    public UserDTO() {
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public List<UserDTO> getFriends() {
        return friends;
    }
    public void setFriends(List<UserDTO> friends) {
        this.friends = friends;
    }
    public List<TripDTO> getTrips() {
        return trips;
    }
    public void setTrips(List<TripDTO> trips) {
        this.trips = trips;
    }
    public List<AchievementDTO> getAchievements() {
        return achievements;
    }
    public void setAchievements(List<AchievementDTO> achievements) {
        this.achievements = achievements;
    }

    public static UserDTO fromJson(JSONObject object) {
        UserDTO user = new UserDTO();
        try {
            user.setId(object.getLong(FIELD_ID));
            user.setName(object.getString(FIELD_NAME));
            user.setPassword(object.getString(FIELD_PASSWORD));

        } catch (JSONException ex) {
            UserMessagesHandler.getInstance().registerError("Error while parsing UserDTO JSON.");
            Log.e(LOG_TAG, ex.toString());
        }

        return user;
    }
}
