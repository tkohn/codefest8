package de.codefest8.gamification.dto;

import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
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
