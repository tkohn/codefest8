package de.codefest8.gamification.controller;

import de.codefest8.gamification.domain.model.Achievement;
import de.codefest8.gamification.domain.model.Trip;
import de.codefest8.gamification.domain.model.TripData;
import de.codefest8.gamification.domain.model.User;
import de.codefest8.gamification.domain.repository.Repository;
import de.codefest8.gamification.domain.repository.RepositoryFactory;

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

    @GET
    @Path("users/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAllUser() {
        Repository repository = RepositoryFactory.getRepository();
        return repository.findAllUser();
    }

    @GET
    @Path("users/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String testData() {
        User user = new User();

        User friend = new User();
        Achievement achievement = new Achievement();
        Trip trip = new Trip();
        TripData tripData = new TripData();

        user.addAchievement(achievement);
        user.addFriend(friend);
        user.addTrip(trip);

        trip.addTripData(tripData);

        user.setName("Peter");
        user.setPassword("none");

        achievement.setName("awesome");
        achievement.setTrip(trip);
        achievement.setPoints(1000);

        tripData.setKMPerLiter(3.3);


        Repository repository = RepositoryFactory.getRepository();
        repository.store(user);
        return "create test data";
    }
/*
    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TestModel> findAllTestModels() {
        Repository repository = RepositoryFactory.getRepository();
        TestModel testModel = new TestModel();
        testModel.setName("Test Test");
        repository.store(testModel);
        return repository.findAllTestModels();
    }

    @GET
    @Path("test/{test_model_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TestModel findTestModel(@PathParam("test_model_id") long test_model_id) {
        Repository repository = RepositoryFactory.getRepository();
        TestModel testModel = new TestModel();
        testModel.setId(test_model_id);
        return repository.findTestModel(testModel);
    }
    */
}
