package de.codefest8.gamification8.models;

import java.util.List;

public class TripDTO {
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
}
