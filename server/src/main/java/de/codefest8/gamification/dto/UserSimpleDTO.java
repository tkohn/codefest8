package de.codefest8.gamification.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

/**
 * Created by torsten on 08/03/15.
 */
public class UserSimpleDTO {

    private long id;
    private String name;
    private String password;
    private long[] friends;
    private long[] trips;
    private long[] achievements;

    public UserSimpleDTO(UserDTO userDTO){
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.trips = getSimpleTrips(userDTO.getTrips());
        this.achievements = getSimpleAchievements(userDTO.getAchievements());
    }

    public UserSimpleDTO() {
    }

    public UserSimpleDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public UserSimpleDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private long[] getSimpleAchievements(List<AchievementDTO> achievements) {
        if(achievements == null){
            return new long[0];
        }
        long[] results = new long[achievements.size()];
        for (int i = 0; i < achievements.size(); i++) {
            results[i] = achievements.get(i).getId();
        }
        return results;
    }

    private long[] getSimpleTrips(List<TripDTO> trips) {
        if(trips == null){
            return new long[0];
        }

        long[] results = new long[trips.size()];
        for(int i=0; i< trips.size(); i++){
            results[i] = trips.get(i).getId();
        }
        return results;
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

    public long[] getFriends() {
        return friends;
    }

    public void setFriends(long[] friends) {
        this.friends = friends;
    }

    public long[] getTrips() {
        return trips;
    }

    public void setTrips(long[] trips) {
        this.trips = trips;
    }

    public long[] getAchievements() {
        return achievements;
    }

    public void setAchievements(long[] achievements) {
        this.achievements = achievements;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
