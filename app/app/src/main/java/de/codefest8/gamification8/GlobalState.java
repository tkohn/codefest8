package de.codefest8.gamification8;


import de.codefest8.gamification8.models.TripDTO;
import de.codefest8.gamification8.models.UserDTO;

public class GlobalState {
    private static GlobalState instance;
    private UserDTO user;
    private UserDTO friend;
    private TripDTO trip;
    private GlobalState() {

    }

    public static GlobalState getInstance() {
        if (instance == null) {
            instance = new GlobalState();
        }

        return instance;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setFriend(UserDTO friend) {
        this.friend = friend;
    }

    public UserDTO getFriend() {
        return friend;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public TripDTO getTrip() {
        return trip;
    }
}
