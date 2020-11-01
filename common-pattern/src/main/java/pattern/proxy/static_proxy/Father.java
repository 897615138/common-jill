package pattern.proxy.static_proxy;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pattern.proxy.Person;

/**
 * @author jill
 */

@AllArgsConstructor
@NoArgsConstructor
public
class Father implements Person {
    private Son son;

    @Override
    public void findLove() {
        System.out.println("父亲物色对象");
        son.findLove();
        System.out.println("双方父母同意，确立关系");
    }

    public void findJob() {

    }


}
