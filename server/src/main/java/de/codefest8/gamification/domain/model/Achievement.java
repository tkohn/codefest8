package de.codefest8.gamification.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by torsten on 07/03/15.
 */
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = Achievement.FIND_BY_ID,
                query = "SELECT e FROM User u, Achievement e WHERE u.id = e.user.id AND e.user.id = :user_id AND e.id = :achievement_id"),
        @NamedQuery(name = Achievement.FIND_ALL, query = "SELECT e FROM Achievement e WHERE e.user.id = :user_id")
})
public class Achievement {

    public static final String FIND_BY_ID = "findAchievement";
    public static final String FIND_ALL = "findAllAchievement";
    public static final String PARAMETER_ACHIEVMENT_ID = "achievement_id";
    @TableGenerator(name = "ACHIEVEMENT_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACHIEVEMENT_GEN")
    private long id;

    private String name;
    private int points;
    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "TRIP_ID")
    @JsonBackReference
    private Trip trip;

    public Achievement() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
