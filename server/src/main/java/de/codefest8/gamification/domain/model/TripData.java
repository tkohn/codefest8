package de.codefest8.gamification.domain.model;

import javax.persistence.*;

/**
 * Created by torsten on 07/03/15.
 */
@Entity
@Table
public class TripData {

    @TableGenerator(name = "TRIP_DATA_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TRIP_DATA_GEN")
    @Column(name = "TRIP_DATA_ID")
    private long id;
}
