package de.codefest8.gamification.controller;

import de.codefest8.gamification.domain.model.Achievement;
import de.codefest8.gamification.domain.model.Trip;
import de.codefest8.gamification.domain.model.TripData;
import de.codefest8.gamification.domain.model.User;
import de.codefest8.gamification.domain.repository.Repository;
import de.codefest8.gamification.domain.repository.RepositoryFactory;
import de.codefest8.gamification.service.Service;
import de.codefest8.gamification.service.ServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    public List<User> findAllUser() {
        return service.findAllUser();
    }

}
