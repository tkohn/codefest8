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
@NamedQueries({
        @NamedQuery(name = Trip.FIND_BY_ID,
                query = "SELECT e FROM User u, Trip e WHERE u.id = e.user.id AND e.user.id = :user_id AND e.id = :trip_id"),
        @NamedQuery(name = Trip.FIND_ALL, query = "SELECT e FROM Trip e WHERE e.user.id = :user_id")
})
public class Trip {

    public static final java.lang.String FIND_ALL = "findAllTrips";
    public static final String FIND_BY_ID = "findTripById";
    public static final String PARAMETER_TRIP_ID = "trip_id";

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

    public void addTripData(TripData tripData) {
        if (this.tripDataList == null) {
            this.tripDataList = new ArrayList<>();
        }
        tripData.setTrip(this);
        this.tripDataList.add(tripData);
    }
}
