package pattern.delegate.simple;

/**
 * @author jill
 */
public class Boss {

    public void command(String command, Leader leader) {
        leader.doing(command);
    }

}

