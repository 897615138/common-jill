package pattern.prototype.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jill
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {

    private Prototype prototype;

    public Prototype startClone(Prototype concretePrototype){
        return concretePrototype.clone();
    }

}
