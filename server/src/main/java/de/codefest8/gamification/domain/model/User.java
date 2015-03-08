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
@Table(name = "CAR_USER")
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_ID, query = "SELECT e FROM User e WHERE e.id = :user_id"),
        @NamedQuery(name = User.FIND_ALL, query = "SELECT e FROM User e"),
        @NamedQuery(name = User.FIND_ALL_FRIENDS, query = "SELECT e.friends FROM User e WHERE e.id = :user_id"),
        @NamedQuery(name = User.AUTHENTICATE, query = "SELECT e FROM User e WHERE e.name = :user_name AND e.password = :user_password")
})
public class User {

    public static final String FIND_BY_ID = "findById";
    public static final String FIND_ALL = "findAll";
    public static final String FIND_ALL_FRIENDS = "findAllFriends";
    public static final String AUTHENTICATE = "authenticate";
    public static final String PARAMETER_USER_ID = "user_id";
    public static final String PARAMETER_NAME = "user_name";
    public static final String PARAMETER_PASSWORD = "user_password";

    @TableGenerator(name = "USER_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GEN")
    private long id;

    private String name;
    private String password;

    @ManyToOne
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<User> friends;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Trip> trips;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Achievement> achievements;

    public User() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public void addFriend(User user) {
        if (this.friends == null) {
            this.friends = new ArrayList<>();
        }
        user.setUser(this);
        this.friends.add(user);
    }

    public void addTrip(Trip trip) {
        if (this.trips == null) {
            this.trips = new ArrayList<>();
        }
        trip.setUser(this);
        this.trips.add(trip);
    }

    public void addAchievement(Achievement achievement) {
        if (this.achievements == null) {
            this.achievements = new ArrayList<>();
        }
        achievement.setUser(this);
        this.achievements.add(achievement);
    }
}
