package de.codefest8.gamification.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
@Entity
@Table
public class User {

    @TableGenerator(name = "USER_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GEN")
    @Column(name = "USER_ID")
    private long id;

    private String name;
    private String password;

    @ManyToOne
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<User> friends;

    @OneToMany(mappedBy = "trip")
    @JsonManagedReference
    private List<Trip> trips;
}
