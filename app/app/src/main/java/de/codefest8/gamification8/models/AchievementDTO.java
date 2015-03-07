package de.codefest8.gamification8.models;

public class AchievementDTO {
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
}
