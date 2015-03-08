package de.codefest8.gamification.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by torsten on 08/03/15.
 */
public class TripSimpleDTO {
    private long id;
    private long user;
    private Timestamp startTime;
    private double routeLength;

    public TripSimpleDTO(TripDTO tripDTO){
        this.id = tripDTO.getId();
        this.user = tripDTO.getUser().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public double getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(double routeLength) {
        this.routeLength = routeLength;
    }
}
