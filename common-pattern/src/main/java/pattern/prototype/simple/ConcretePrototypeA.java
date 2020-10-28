package pattern.prototype.simple;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author jill
 */

@Data
public class ConcretePrototypeA implements Prototype {

    private int age;
    private String name;
    private ArrayList<String> hobbies = new ArrayList<>();

    @Override
    public ConcretePrototypeA clone() {
        ConcretePrototypeA concretePrototype = new ConcretePrototypeA();
        concretePrototype.setAge(this.age);
        concretePrototype.setName(this.name);
        concretePrototype.setHobbies(this.hobbies);
        return concretePrototype;
    }
}
