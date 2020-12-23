package jill.common.model.async;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.concurrent.TimeUnit;

/**
 * RxJavaWithFlatMap稳定
 * <p>
 * 1000
 * RxJavaWithParallel TPS: 1618
 * RxJavaWithFlatMap TPS: 7518
 * RxJavaWithBlockingScheduler TPS: 3597
 * RxJavaWithBlocking TPS: 4807
 * RxJavaWithoutBlocking TPS: 500000
 *
 * @author Jill W
 * @date 2020/12/23
 */
public class RxJavaTestTest extends TestCase {
    public static int count = 10000;

    public void testTestRxJavaWithoutBlocking() {
        try {
            RxJavaTest.testRxJavaWithoutBlocking(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testTestRxJavaWithBlockingScheduler() {
        try {
            //336
            RxJavaTest.testRxJavaWithBlockingScheduler(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testTestRxJavaWithBlocking() {
        try {
            //187
            RxJavaTest.testRxJavaWithBlocking(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testTestRxJavaWithFlatMap() {
        try {
            //248
            RxJavaTest.testRxJavaWithFlatMap(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testTestRxJavaWithParallel() {
        try {
            //130
            RxJavaTest.testRxJavaWithParallel(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testTest() {
        Disposable subscribe = Observable.just(1, 2, 3)
                .delay(1, TimeUnit.SECONDS)
                //无效的调度 因为delay中已经有computation调度
                .subscribeOn(Schedulers.newThread())
                .map(i -> {
                    System.out.println("map: " + Thread.currentThread().getName());
                    return i;
                })
                .subscribe(i -> {
                });
        Assert.assertNotNull(subscribe);
    }
}