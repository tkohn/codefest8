package de.codefest8.gamification.service;

import de.codefest8.gamification.dto.*;

import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
public interface Service {

    // ##### ##### ##### ##### User ##### ##### ##### #####

    List<UserSimpleDTO> findAllUser();

    UserSimpleDTO findUser(UserDTO userDTO);


    // ##### ##### ##### ##### Achievement ##### ##### ##### #####
    List<AchievementDTO> findAllAchievements(UserDTO userDTO);

    AchievementDTO findAchievement(UserDTO userDTO, AchievementDTO achievementDTO);


    // ##### ##### ##### ##### Trip ##### ##### ##### #####
    List<TripSimpleDTO> findAllTrips(UserDTO userDTO);

    TripSimpleDTO findTrip(UserDTO userDTO, TripDTO tripDTO);

    double[][] getTripPositions(TripDTO tripDTO);
}
