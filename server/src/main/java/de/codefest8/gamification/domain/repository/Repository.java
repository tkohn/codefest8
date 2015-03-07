package de.codefest8.gamification.domain.repository;

import de.codefest8.gamification.domain.model.User;

import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
public interface Repository {

    User store(User user);

    User findUser(User user);

    List<User> findAllUser();

    List<User> findAllFriends(User user);
}
