package de.codefest8.gamification.service;

import de.codefest8.gamification.domain.model.Trip;
import de.codefest8.gamification.domain.model.User;
import de.codefest8.gamification.domain.repository.Repository;
import de.codefest8.gamification.domain.repository.RepositoryFactory;
import de.codefest8.gamification.dto.TripDTO;
import de.codefest8.gamification.dto.UserDTO;
import de.codefest8.gamification.dto.UserSimpleDTO;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

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
    public UserDTO findUser(UserDTO userDTO) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        User result = mapper.map(userDTO, User.class);
        result = repository.findUser(result);
        return mapper.map(result, UserDTO.class);
    }

    // ##### ##### ##### ##### Trip ##### ##### ##### #####

    @Override
    public List<TripDTO> findAllTrips(UserDTO userDTO) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        List<TripDTO> tripDTOs = new ArrayList<>();
        for (Trip elem : repository.findAllTrips(mapper.map(userDTO, User.class))) {
            tripDTOs.add(mapper.map(elem, TripDTO.class));
        }
        return tripDTOs;
    }

    @Override
    public TripDTO findTrip(UserDTO userDTO, TripDTO tripDTO) {
        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        Trip result = mapper.map(tripDTO, Trip.class);
        result = repository.findTrip(mapper.map(userDTO, User.class), result);
        return mapper.map(result, TripDTO.class);
    }
}
