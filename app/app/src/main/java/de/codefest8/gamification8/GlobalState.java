package de.codefest8.gamification8;


import de.codefest8.gamification8.models.UserDTO;

public class GlobalState {
    private static GlobalState instance;
    private UserDTO user;
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
}
