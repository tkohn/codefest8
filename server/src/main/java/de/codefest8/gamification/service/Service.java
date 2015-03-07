package de.codefest8.gamification.service;

import de.codefest8.gamification.domain.model.User;
import de.codefest8.gamification.dto.UserDTO;

import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
public interface Service {

    List<User> findAllUser();
}
