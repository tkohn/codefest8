package de.codefest8.gamification8;

public class GlobalState {

    private static GlobalState instance;

    private long currentUserId;

    private GlobalState() {
        this.currentUserId = 1;
    }

    public static GlobalState getInstance() {
        if (instance == null) {
            instance = new GlobalState();
        }
        return instance;
    }

    public long getCurrentUserId()
    {
        return this.currentUserId;
    }

}
