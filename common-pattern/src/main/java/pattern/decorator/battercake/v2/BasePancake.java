package pattern.decorator.battercake.v2;

/**
 * @author jill
 */
public class BasePancake extends Pancake {
    @Override
    protected String getMsg(){
        return "煎饼";
    }

    @Override
    public int getPrice(){
        return 5;
    }
}
