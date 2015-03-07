package de.codefest8.gamification.controller;

import de.codefest8.gamification.dto.TripDTO;
import de.codefest8.gamification.dto.UserDTO;
import de.codefest8.gamification.dto.UserSimpleDTO;
import de.codefest8.gamification.service.Service;
import de.codefest8.gamification.service.ServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    @GET
    @Path("users/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserSimpleDTO> findAllUser() {
        return service.findAllUser();
    }

    @GET
    @Path("users/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO findUser(@PathParam("user_id") long user_id) {
        return service.findUser(new UserDTO(user_id));
    }

    @GET
    @Path("users/{user_id}/trips")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TripDTO> findAllTrips(@PathParam("user_id") long user_id) {
        return service.findAllTrips(new UserDTO(user_id));
    }

}

