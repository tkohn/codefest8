package de.codefest8.gamification.controller;

import de.codefest8.gamification.domain.model.TestModel;
import de.codefest8.gamification.domain.repository.Repository;
import de.codefest8.gamification.domain.repository.RepositoryFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
@Path("/aix-cruise/api/v1")
public class RestfulController {

    @GET
    @Path("users/{user_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String findUser(@PathParam("user_id") long user_id) {
        return "Deine ID war: " + user_id;
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
