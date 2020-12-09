package jill.common.model.async;

import java.util.concurrent.Callable;

/**
 * @author Jill W
 * @date 2020/11/17
 */
public class Task implements Callable<String> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        return "time:" + System.currentTimeMillis();
    }
}
