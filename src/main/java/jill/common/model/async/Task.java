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
     */
    @Override
    public String call() {
        return "time:" + System.currentTimeMillis();
    }
}
