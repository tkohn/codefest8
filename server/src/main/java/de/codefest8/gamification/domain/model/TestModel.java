package de.codefest8.gamification.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by torsten on 07/03/15.
 */
@Entity
@Table(name = "TEST")
@NamedQueries({
        @NamedQuery(name = TestModel.FIND_BY_ID, query = "SELECT e FROM TestModel e WHERE e.id = :test_model_id"),
        @NamedQuery(name = TestModel.FIND_ALL, query = "SELECT e FROM TestModel e")
})
public class TestModel implements Serializable {
    public static final String FIND_BY_ID = "findTestModel";
    public static final String FIND_ALL = "findAllTestModels";
    public static final String PARAMETER_TEST_MODEL_ID = "test_model_id";
    @TableGenerator(name = "TEST_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TEST_GEN")
    @Column(name = "TEST_ID")
    private long id;

    @NotNull
    @Column(length = 64)
    private String name;

    public TestModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestModel)) return false;

        TestModel testModel = (TestModel) o;

        if (id != testModel.id) return false;
        if (name != null ? !name.equals(testModel.name) : testModel.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
