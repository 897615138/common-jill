package jill.mockito.code.customMatcher;

import jill.mockito.code.ArgumentMatcher;
import jill.mockito.model.TestModel;
import lombok.Builder;

import java.util.Date;

/**
 * @author JillW
 * @date 2020/09/30
 */
@Builder
public class TestMatcher extends ArgumentMatcher<TestModel> {
    private final String name;
    private final int id;
    private final Date birthday;

    public TestMatcher(String name, int id, Date birthday) {
        this.name = name;
        this.id = id;
        this.birthday = birthday;
    }

    public boolean matches(Object argument) {
        if (!(argument instanceof TestModel)) {
            return false;
        }
        TestModel e = (TestModel) argument;

        return name.equals(e.getName()) &&
                id == e.getId() && birthday.equals(e.getBirthday());
    }
}
