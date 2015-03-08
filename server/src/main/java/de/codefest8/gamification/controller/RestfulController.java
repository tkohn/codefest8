package de.codefest8.gamification.controller;

import de.codefest8.gamification.dto.*;
import de.codefest8.gamification.service.Service;
import de.codefest8.gamification.service.ServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
@Path("/aix-cruise/api/v1")
public class RestfulController {

    private Service service;

    public RestfulController() {
        this.service = new ServiceImpl();
    }

    @POST
    @Path("login/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserSimpleDTO login(UserSimpleDTO userSimpleDTO){
        UserDTO userDTO = new UserDTO();
        userDTO.setName(userSimpleDTO.getName());
        userDTO.setPassword(userSimpleDTO.getPassword());
        return service.authenticate(userDTO);
    }

    @GET
    @Path("users/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserSimpleDTO> findAllUser() {
        return service.findAllUser();
    }

    @GET
    @Path("users/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserSimpleDTO findUser(@PathParam("user_id") long user_id) {
        return service.findUser(new UserDTO(user_id));
    }

    @GET
    @Path("users/{user_id}/friends")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserSimpleDTO> findAllFriends(@PathParam("user_id") long user_id) {
        return service.findAllFriends(new UserDTO(user_id));
    }

    @GET
    @Path("users/{user_id}/achievements")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AchievementDTO> findAllAchievements(@PathParam("user_id") long user_id) {
        return service.findAllAchievements(new UserDTO(user_id));
    }

    @GET
    @Path("users/{user_id}/achievements/{achievement_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public AchievementDTO findAllAchievement(@PathParam("user_id") long user_id, @PathParam("achievement_id") long achievement_id) {
        return service.findAchievement(new UserDTO(user_id), new AchievementDTO(achievement_id));
    }

    @GET
    @Path("users/{user_id}/trips")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TripSimpleDTO> findAllTrips(@PathParam("user_id") long user_id) {
        return service.findAllTrips(new UserDTO(user_id));
    }

    @GET
    @Path("users/{user_id}/trips/{trip_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TripSimpleDTO findTrip(@PathParam("user_id") long user_id, @PathParam("trip_id") long trip_id) {
        return service.findTrip(new UserDTO(user_id), new TripDTO(trip_id));
    }

    @GET
    @Path("users/{user_id}/trips/{trip_id}/fuel")
    @Produces(MediaType.APPLICATION_JSON)
    public TripDataFuelDTO getFuelEconomy(@PathParam("user_id") long user_id, @PathParam("trip_id") long trip_id) {
        return service.getTripFuelEconomy(new TripDTO(trip_id));
    }

    @GET
    @Path("users/{user_id}/trips/{trip_id}/data")
    @Produces(MediaType.APPLICATION_JSON)
    public double[][] getTripData(@PathParam("user_id") long user_id,
                                       @PathParam("trip_id") long trip_id) {
        return service.getTripPositions(new TripDTO(trip_id));
    }

}

