package de.codefest8.gamification.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by torsten on 07/03/15.
 */
@Entity
@Table
public class Trip {

    @TableGenerator(name = "TRIP_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TRIP_GEN")
    @Column(name = "TRIP_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;
}
