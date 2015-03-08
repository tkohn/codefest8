package de.codefest8.gamification.domain.repository;

import de.codefest8.gamification.domain.model.Achievement;
import de.codefest8.gamification.domain.model.Trip;
import de.codefest8.gamification.domain.model.TripData;
import de.codefest8.gamification.domain.model.User;
import de.codefest8.gamification.dto.TripDTO;
import de.codefest8.gamification.dto.TripDataFuelDTO;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
public interface Repository {

    // ##### ##### ##### ##### User ##### ##### ##### #####

    User authenticate(User user);
    User store(User user);

    User findUser(User user);

    List<User> findAllUsers();

    List<User> findAllFriends(User user);

    // ##### ##### ##### ##### Achievement ##### ##### ##### #####

    List<Achievement> findAllAchievement(User User);

    Achievement findAchievement(User user, Achievement achievement);

    // ##### ##### ##### ##### Trip ##### ##### ##### #####

    List<Trip> findAllTrips(User user);

    Trip findTrip(User user, Trip trip);

    // ##### ##### ##### ##### TripData ##### ##### ##### #####

    double getRouteLength(Trip trip);
    Timestamp getStartTime(Trip trip);
    double[][] getTripPositions(Trip trip);
    TripDataFuelDTO getTripFuelEconomy(Trip trip);
}
