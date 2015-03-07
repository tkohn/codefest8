package de.codefest8.gamification.service;

import de.codefest8.gamification.dto.TripDTO;
import de.codefest8.gamification.dto.UserDTO;
import de.codefest8.gamification.dto.UserSimpleDTO;

import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
public interface Service {

    // ##### ##### ##### ##### User ##### ##### ##### #####

    List<UserSimpleDTO> findAllUser();

    UserDTO findUser(UserDTO userDTO);

    // ##### ##### ##### ##### Trip ##### ##### ##### #####
    List<TripDTO> findAllTrips(UserDTO userDTO);

    TripDTO findTrip(UserDTO userDTO, TripDTO tripDTO);
}
