package de.codefest8.gamification.service;

import de.codefest8.gamification.domain.model.Achievement;
import de.codefest8.gamification.domain.model.Trip;
import de.codefest8.gamification.domain.model.User;
import de.codefest8.gamification.domain.repository.Repository;
import de.codefest8.gamification.domain.repository.RepositoryFactory;
import de.codefest8.gamification.dto.*;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
public class ServiceImpl implements Service {
    private Repository repository;

    public ServiceImpl() {
        this.repository = RepositoryFactory.getRepository();
    }

    // ##### ##### ##### ##### User ##### ##### ##### #####
    @Override
    public List<UserSimpleDTO> findAllUser() {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        List<UserSimpleDTO> userSimpleDTOs = new ArrayList<>();
        for (User elem : repository.findAllUsers()) {
            userSimpleDTOs.add(new UserSimpleDTO(mapper.map(elem, UserDTO.class)));
        }
        return userSimpleDTOs;
    }

    @Override
    public UserSimpleDTO findUser(UserDTO userDTO) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        User result = mapper.map(userDTO, User.class);
        result = repository.findUser(result);
        return new UserSimpleDTO(mapper.map(result, UserDTO.class));
    }


    // ##### ##### ##### ##### Achievement ##### ##### ##### #####

    @Override
    public List<AchievementDTO> findAllAchievements(UserDTO userDTO) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        List<AchievementDTO> achievementDTOs = new ArrayList<>();
        for (Achievement elem : repository.findAllAchievement(mapper.map(userDTO, User.class))) {
            achievementDTOs.add(mapper.map(elem, AchievementDTO.class));
        }
        return achievementDTOs;
    }

    @Override
    public AchievementDTO findAchievement(UserDTO userDTO, AchievementDTO achievementDTO) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Achievement result = mapper.map(achievementDTO, Achievement.class);
        result = repository.findAchievement(mapper.map(userDTO, User.class), result);
        return mapper.map(result, AchievementDTO.class);
    }

    @Override
    public double[][] getTripPositions(TripDTO tripDTO) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        return repository.getTripPositions(mapper.map(tripDTO, Trip.class));
    }

    // ##### ##### ##### ##### Trip ##### ##### ##### #####

    @Override
    public List<TripSimpleDTO> findAllTrips(UserDTO userDTO) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        List<TripSimpleDTO> tripDTOs = new ArrayList<>();
        TripSimpleDTO tempTripSimpleDTO;
        double routeLength;
        Timestamp startTime;
        for (Trip elem : repository.findAllTrips(mapper.map(userDTO, User.class))) {
            tempTripSimpleDTO = new TripSimpleDTO(mapper.map(elem, TripDTO.class));
            tempTripSimpleDTO.setRouteLength(repository.getRouteLength(elem));
            tempTripSimpleDTO.setStartTime(repository.getStartTime(elem));
            tripDTOs.add(tempTripSimpleDTO);
        }
        return tripDTOs;
    }

    @Override
    public TripSimpleDTO findTrip(UserDTO userDTO, TripDTO tripDTO) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Trip result = mapper.map(tripDTO, Trip.class);
        TripSimpleDTO tempTripSimpleDTO;
        result = repository.findTrip(mapper.map(userDTO, User.class), result);

        tempTripSimpleDTO = new TripSimpleDTO(mapper.map(result, TripDTO.class));
        tempTripSimpleDTO.setRouteLength(repository.getRouteLength(result));
        tempTripSimpleDTO.setStartTime(repository.getStartTime(result));
        return tempTripSimpleDTO;
    }
}
