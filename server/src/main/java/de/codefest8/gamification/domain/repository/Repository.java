package de.codefest8.gamification.domain.repository;

import de.codefest8.gamification.domain.model.Trip;
import de.codefest8.gamification.domain.model.User;

import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
public interface Repository {

    // ##### ##### ##### ##### User ##### ##### ##### #####

    User store(User user);

    User findUser(User user);

    List<User> findAllUsers();

    List<User> findAllFriends(User user);

    // ##### ##### ##### ##### Trip ##### ##### ##### #####

    List<Trip> findAllTrips(User user);

    Trip findTrip(User user, Trip trip);
}
