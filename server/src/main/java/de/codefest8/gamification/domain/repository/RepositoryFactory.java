package de.codefest8.gamification.domain.repository;


import de.codefest8.gamification.domain.repository.jpa.JPARepositoryImpl;

/**
 * Created by torsten on 20/02/15.
 */
public class RepositoryFactory {
    public static Repository getRepository() {
        return JPARepositoryImpl.getInstance();
    }
}
