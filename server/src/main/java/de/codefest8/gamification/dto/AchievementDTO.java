package de.codefest8.gamification.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Created by torsten on 07/03/15.
 */
public class AchievementDTO {
    private long id;
    private String name;
    private int points;
    private String description;
    @JsonBackReference
    private UserDTO user;
    @JsonBackReference
    private TripDTO trip;

    public AchievementDTO() {
    }

    public AchievementDTO(long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
