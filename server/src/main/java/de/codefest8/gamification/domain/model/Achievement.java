package de.codefest8.gamification.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by torsten on 07/03/15.
 */
@Entity
@Table
public class Achievement {

    @TableGenerator(name = "ACHIEVEMENT_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACHIEVEMENT_GEN")
    @Column(name = "ACHIEVEMENT_ID")
    private long id;

    private String name;
    private int points;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "TRIP_ID")
    @JsonBackReference
    private Trip trip;
}
