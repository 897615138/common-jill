package jill.common.util;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * @author Jill W
 * @date 2021/03/04
 */
@Slf4j
public class ParallelUtil {

    @FunctionalInterface
    public interface ParallelFunction<T> extends Supplier<T> {
    }

    @Data
    @Accessors(chain = true)
    public static class ParallelJob<T> {
        private ParallelFunction<T> function;
        private T result;
    }

    @SafeVarargs
    public static void execute(@NonNull ParallelJob<T>... jobs) {
        Arrays.stream(jobs).parallel().forEach(job -> job.setResult(job.getFunction().get()));
    }
}
