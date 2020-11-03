package pattern.singleton.threadlocal;

/**
 * @author jill
 */
public class ThreadLocalSingleton {
    private static final ThreadLocal<ThreadLocalSingleton> THREAD_LOCAL_INSTANCE =
            ThreadLocal.withInitial(ThreadLocalSingleton::new);

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance() {
        return THREAD_LOCAL_INSTANCE.get();
    }

    public static void remove() {
        THREAD_LOCAL_INSTANCE.remove();
    }
}
