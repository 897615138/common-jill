package jill.common.model.async;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author Jill W
 * @date 2020/12/30
 */
@Slf4j
public class CompletedFutureProtoTest {
    /**
     * 1 创建一个完成的CompletableFuture
     */
    @Test
    public void completedFutureExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
        Assert.assertTrue(cf.isDone());
        Assert.assertEquals("message", cf.getNow(null));
    }

    /**
     * 2 运行一个简单的异步阶段
     * CompletableFuture的方法如果以Async结尾，它会异步的执行(没有指定executor的情况下)
     * 异步执行通过ForkJoinPool实现， 它使用守护线程去执行任务。
     */
    @Test
    public void runAsyncExample() {
        CompletableFuture<?> cf = CompletableFuture.runAsync(() -> {
            Assert.assertTrue(Thread.currentThread().isDaemon());
//            randomSleep();
        });
        Assert.assertFalse(cf.isDone());
//        sleepEnough();
        Assert.assertTrue(cf.isDone());
    }

    /**
     * 3 在前一个阶段上应用函数
     * #1 的完成的CompletableFuture， #1返回结果为字符串message,然后应用一个函数把它变成大写字母。
     * then意味着这个阶段的动作发生当前的阶段正常完成之后。
     * Apply意味着返回的阶段将会对结果前一阶段的结果应用一个函数。
     */
    @Test
    public void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            Assert.assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
//        Assert.assertEquals("MESSAGE", cf.getNow(null));
    }

    /**
     * 4、在前一个阶段上异步应用函数
     * 通过调用异步方法(方法后边加Async后缀)，串联起来的CompletableFuture可以异步地执行（使用ForkJoinPool.commonPool()）。
     */
    @Test
    public void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            Assert.assertTrue(Thread.currentThread().isDaemon());
//            randomSleep();
            return s.toUpperCase();
        });
//        Assert.assertNull(cf.getNow(null));
        Assert.assertEquals("MESSAGE", cf.join());
    }

}
