package de.codefest8.gamification.domain.repository;

import de.codefest8.gamification.domain.model.TestModel;

import java.util.List;

/**
 * Created by torsten on 07/03/15.
 */
public interface Repository {

    TestModel store(TestModel testModel);
    TestModel findTestModel(TestModel testModel);
    List<TestModel> findAllTestModels();
}
