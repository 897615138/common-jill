package pattern.proxy.s;


import pattern.proxy.Person;

/**
 * @author jill on 2019/3/10.
 */
public
class Son implements Person {

    @Override
    public void findLove() {
        System.out.println("儿子要求：肤白貌美大长腿");
    }


    @SuppressWarnings("EmptyMethod")
    public void findJob() {

    }

    @SuppressWarnings("EmptyMethod")
    public void eat() {

    }

}
