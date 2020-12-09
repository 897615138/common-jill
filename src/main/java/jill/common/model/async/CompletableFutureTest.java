package jill.common.model.async;

import jill.common.constant.NumConstant;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Jill W
 * @date 2020/11/17
 */
public class CompletableFutureTest {
    private static final String TEST = "TEST";

    /**
     * 随机数模拟价格
     *
     * @return 价格
     */
    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (Math.random() < NumConstant.POINT_THREE) {
            throw new RuntimeException("fetch price failed!");
        }
        return NumConstant.POINT_THREE + Math.random() * 23;
    }

    static String queryCode(@SuppressWarnings("SameParameterValue") String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
        return Objects.equals(name, TEST) ? "1" : "2";
    }

    /**
     * 实现了Supplier接口的对象 静态方法的签名符合Supplier接口的定义
     *
     * @param times 次数
     * @throws Exception 异常
     */
    public static void getPriceManyTimes(Integer times, String name) throws Exception {
        for (int i = 0; i < times; i++) {
            getPrice(name);
        }
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    public static Double getPrice(String name) {
        System.out.println(name);
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureTest::fetchPrice);
        // 如果执行成功:完成时，CompletableFuture会调用Consumer对象: accept
        cf.thenAccept((result) ->
                System.out.println("thread:" + Thread.currentThread().getName() + " price: " + result));
        // 如果执行异常:异常时，CompletableFuture会调用Function对象：apply
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        try {
            return cf.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("SimpleTest:");
        System.out.println();
        simpleTest();
        System.out.println();
        System.out.println("SerialTest:");
        serialTest();
        System.out.println();

    }

    private static void simpleTest() {
        try {
            getPriceManyTimes(10, TEST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void serialTest() {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> queryCode(TEST));
        CompletableFuture<Double> twice = first.thenApply(CompletableFutureTest::getPrice);
        CompletableFuture<Object> third = twice.thenApply((price) -> {
            System.out.println("price:" + price);
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
