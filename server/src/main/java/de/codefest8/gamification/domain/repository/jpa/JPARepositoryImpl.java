package de.codefest8.gamification.domain.repository.jpa;

import de.codefest8.gamification.domain.model.Achievement;
import de.codefest8.gamification.domain.model.Trip;
import de.codefest8.gamification.domain.model.TripData;
import de.codefest8.gamification.domain.model.User;
import de.codefest8.gamification.domain.repository.Repository;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
public class JPARepositoryImpl implements Repository {
    private static final String PERSISTENCE_UNIT_NAME = "aix-cruise-db";
    private static JPARepositoryImpl ourInstance = new JPARepositoryImpl();
    private EntityManagerFactory factory;

    public static JPARepositoryImpl getInstance() {
        return ourInstance;
    }

    private JPARepositoryImpl() {
        this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    // ##### ##### ##### ##### User ##### ##### ##### #####

    @Override
    public User store(User user) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        User result = manager.merge(user);
        manager.getTransaction().commit();
        manager.close();
        return result;
    }

    @Override
    public User findUser(User user) {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<User> query = manager.createNamedQuery(User.FIND_BY_ID, User.class);
        query.setParameter(User.PARAMETER_USER_ID, user.getId());
        User result = query.getSingleResult();
        manager.close();
        return result;
    }

    @Override
    public List<User> findAllUsers() {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<User> query = manager.createNamedQuery(User.FIND_ALL, User.class);
        List<User> results = query.getResultList();
        manager.close();
        return results;
    }

    @Override
    public List<User> findAllFriends(User user) {
        return null;
    }

    @Override
    public List<Achievement> findAllAchievement(User user) {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Achievement> query = manager.createNamedQuery(Achievement.FIND_ALL, Achievement.class);
        query.setParameter(User.PARAMETER_USER_ID, user.getId());
        List<Achievement> results = query.getResultList();
        manager.close();
        return results;
    }

    @Override
    public Achievement findAchievement(User user, Achievement achievement) {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Achievement> query = manager.createNamedQuery(Achievement.FIND_BY_ID, Achievement.class);
        query.setParameter(User.PARAMETER_USER_ID, user.getId());
        query.setParameter(Achievement.PARAMETER_ACHIEVMENT_ID, achievement.getId());
        Achievement result = query.getSingleResult();
        manager.close();
        return result;
    }

    // ##### ##### ##### ##### Trip ##### ##### ##### #####
    @Override
    public List<Trip> findAllTrips(User user) {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Trip> query = manager.createNamedQuery(Trip.FIND_ALL, Trip.class);
        query.setParameter(User.PARAMETER_USER_ID, user.getId());
        List<Trip> results = query.getResultList();
        manager.close();
        return results;
    }

    @Override
    public Trip findTrip(User user, Trip trip) {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<Trip> query = manager.createNamedQuery(Trip.FIND_BY_ID, Trip.class);
        query.setParameter(Trip.PARAMETER_TRIP_ID, trip.getId());
        query.setParameter(User.PARAMETER_USER_ID, user.getId());
        Trip result = query.getSingleResult();
        manager.close();
        return result;
    }

    // ##### ##### ##### ##### Trip ##### ##### ##### #####

    // TODO fix this bullshit
    @Override
    public double getRouteLength(Trip trip) {
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNativeQuery("select ST_Length(ST_MakeLine(gps.position)::geography) from (select position from trip_data where trip_id = ? order by datetime asc) as gps;");
        query.setParameter(1, trip.getId());
        double result = (double) query.getSingleResult();
        manager.close();
        return result;
    }

    @Override
    public Timestamp getStartTime(Trip trip) {
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNativeQuery("select datetime from trip_data where trip_id = ? order by datetime  asc limit 1;");
        query.setParameter(1, trip.getId());
        Timestamp result = (Timestamp) query.getSingleResult();
        manager.close();
        return result;
    }

    @Override
    public double[][][][] getTripPositions(Trip trip, String property) {
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNativeQuery("select ST_X(position::geometry) as long, ST_Y(position::geometry) as lat, ? as property from trip_data where trip_id = ?;");
        query.setParameter(1, trip.getId());
        query.setParameter(2, property);
        List<Object[]> results = query.getResultList();
        results.get(0);
        results.get(0);
        results.get(0);
        results.get(0);

        manager.close();
        return new double[0][0][0][0];
    }
}
