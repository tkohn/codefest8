package de.codefest8.gamification.domain.repository.jpa;

import de.codefest8.gamification.domain.model.User;
import de.codefest8.gamification.domain.repository.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
/*@Override
    public TestModel store(TestModel testModel) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        TestModel result = manager.merge(testModel);
        manager.getTransaction().commit();
        manager.close();
        return result;
    }
@Override
    public TestModel findTestModel(TestModel testModel){
        EntityManager manager = factory.createEntityManager();
        TypedQuery<TestModel> query = manager.createNamedQuery(TestModel.FIND_BY_ID, TestModel.class);
        query.setParameter(TestModel.PARAMETER_TEST_MODEL_ID, testModel.getId());
        TestModel result = query.getSingleResult();
        manager.close();
        return result;
    }
@Override
    public List<TestModel> findAllTestModels(){
        EntityManager manager = factory.createEntityManager();
        TypedQuery<TestModel> query = manager.createNamedQuery(TestModel.FIND_ALL, TestModel.class);
        List<TestModel> results = query.getResultList();
        manager.close();
        return results;
    }*/

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
        return null;
    }

    @Override
    public List<User> findAllUser() {
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
}
