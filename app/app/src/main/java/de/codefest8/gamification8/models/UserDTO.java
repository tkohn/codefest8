package de.codefest8.gamification8.models;

import java.util.List;

public class UserDTO {
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
}
