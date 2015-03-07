package de.codefest8.gamification.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
@Entity
@Table
public class Trip {

    @TableGenerator(name = "TRIP_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TRIP_GEN")
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "trip")
    @JsonManagedReference
    private List<TripData> tripDataList;

    public Trip() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TripData> getTripDataList() {
        return tripDataList;
    }

    public void setTripDataList(List<TripData> tripDataList) {
        this.tripDataList = tripDataList;
    }

    public void addTripData(TripData tripData){
        if(this.tripDataList == null){
            this.tripDataList = new ArrayList<>();
        }
        tripData.setTrip(this);
        this.tripDataList.add(tripData);
    }
}
