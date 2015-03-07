package de.codefest8.gamification.service;

import de.codefest8.gamification.domain.model.User;
import de.codefest8.gamification.domain.repository.Repository;
import de.codefest8.gamification.domain.repository.RepositoryFactory;

import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
public class ServiceImpl implements Service {
    private Repository repository;

    public ServiceImpl() {
        this.repository = RepositoryFactory.getRepository();
    }


    @Override
    public List<User> findAllUser() {
        return repository.findAllUser();
    }
}
