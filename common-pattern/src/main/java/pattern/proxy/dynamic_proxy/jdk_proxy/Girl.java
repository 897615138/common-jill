package pattern.proxy.dynamic_proxy.jdk_proxy;


import pattern.proxy.Person;

/**
 * @author jill
 */
public
class Girl implements Person {
    @Override
    public void findLove() {
        System.out.println("男");
    }
}
