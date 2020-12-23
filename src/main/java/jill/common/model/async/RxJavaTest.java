package jill.common.model.async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jill W
 * @date 2020/12/23
 */
public class RxJavaTest {
    /**
     * 基本的RxJava的使用，利用Range创建一个Observable, subscriber处理接收的数据。
     * 因为整个逻辑没有阻塞，程序运行起来很快
     *
     * @param count count
     * @throws Exception 异常
     */
    public static void testRxJavaWithoutBlocking(int count) throws Exception {
        CountDownLatch finishedLatch = new CountDownLatch(1);
        long t = System.nanoTime();
        Observable.range(0, count).map(i ->
                //System.out.println("A:" + Thread.currentThread().getName());
                i + 1
        ).subscribe(statusCode -> {
            //System.out.println("B:" + Thread.currentThread().getName());
        }, error -> {
        }, finishedLatch::countDown);
        finishedLatch.await();
        //ms
        t = (System.nanoTime() - t) / 1000000;
        System.out.println("RxJavaWithoutBlocking TPS: " + count * 1000 / t);
    }

    /**
     * 模拟一下实际的应用，加上业务处理。
     * 业务逻辑是发送一个http的请求，httpserver是一个模拟器，针对每个请求有30毫秒的延迟。
     *
     * @param count count
     * @throws Exception 异常
     */
    public static void testRxJavaWithBlocking(int count) throws Exception {
        URL url = new URL("http://127.0.0.1:8999/");
        CountDownLatch finishedLatch = new CountDownLatch(1);
        long t = System.nanoTime();
        Observable.range(0, count).map(i -> {
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
//                while ((inputLine = in.readLine()) != null) {
//                    //response.append(inputLine);
//                }
                in.close();
                return responseCode;
            } catch (Exception ex) {
                return -1;
            }
        }).subscribe(statusCode -> {
        }, error -> {
        }, finishedLatch::countDown);
        finishedLatch.await();
        t = (System.nanoTime() - t) / 1000000; //ms
        System.out.println("RxJavaWithBlocking TPS: " + count * 1000 / t);
    }

    /**
     * 加上调度器 性能不会有太多改观
     *
     * @param count count
     * @throws Exception 异常
     */
    public static void testRxJavaWithBlockingScheduler(int count) throws Exception {
        URL url = new URL("http://127.0.0.1:8999/");
        CountDownLatch finishedLatch = new CountDownLatch(1);
        long t = System.nanoTime();
        Observable.range(0, count).map(i -> {
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
//                while ((inputLine = in.readLine()) != null) {
//                    //response.append(inputLine);
//                }
                in.close();
                return responseCode;
            } catch (Exception ex) {
                return -1;
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).subscribe(statusCode -> {
        }, error -> {
        }, finishedLatch::countDown);
        finishedLatch.await();
        t = (System.nanoTime() - t) / 1000000; //ms
        System.out.println("RxJavaWithBlockingScheduler TPS: " + count * 1000 / t);
    }

    /**
     * 通过flatmap可以将源Observable的元素项转成n个Observable,生成的每个Observable可以使用线程池并发的执行，
     * 同时flatmap还会将这n个Observable merge成一个Observable
     *
     * @param count count
     * @throws Exception 异常
     */
    public static void testRxJavaWithFlatMap(int count) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(200, new ThreadFactoryBuilder().setNameFormat("SubscribeOn-%d").build());
        URL url = new URL("http://127.0.0.1:8999/");
        CountDownLatch finishedLatch = new CountDownLatch(1);
        long t = System.nanoTime();
        Observable.range(0, count).subscribeOn(Schedulers.io()).flatMap(i ->
                        //System.out.println("A: " + Thread.currentThread().getName());
                        Observable.just(i).subscribeOn(Schedulers.from(es)).map(v -> {
                                    //System.out.println("B: " + Thread.currentThread().getName());
                                    try {
                                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                        conn.setRequestMethod("GET");
                                        int responseCode = conn.getResponseCode();
                                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                        String inputLine;
//                                while ((inputLine = in.readLine()) != null) {
//                                    response.append(inputLine);
//                                }
                                        in.close();
                                        return responseCode;
                                    } catch (Exception ex) {
                                        return -1;
                                    }
                                }
                        )
        ).observeOn(Schedulers.computation()).subscribe(statusCode -> {
            //System.out.println("C: " + Thread.currentThread().getName());
        }, error -> {
        }, finishedLatch::countDown);
        finishedLatch.await();
        //ms
        t = (System.nanoTime() - t) / 1000000;
        System.out.println("RxJavaWithFlatMap TPS: " + count * 1000 / t);
        es.shutdownNow();
    }

    /**
     * 直接创建多个Observable
     *
     * @param count count
     * @throws Exception 异常
     */
    public static void testRxJavaWithParallel(int count) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(200, new ThreadFactoryBuilder().setNameFormat("SubscribeOn-%d").build());
        URL url = new URL("http://127.0.0.1:8999/");
        CountDownLatch finishedLatch = new CountDownLatch(count);
        long t = System.nanoTime();
        for (int k = 0; k < count; k++) {
            Observable.just(k).map(i -> {
                //System.out.println("A: " + Thread.currentThread().getName());
                try {
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    int responseCode = conn.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
//                    while ((inputLine = in.readLine()) != null) {
//                        //response.append(inputLine);
//                    }
                    in.close();
                    return responseCode;
                } catch (Exception ex) {
                    return -1;
                }
            }).subscribeOn(Schedulers.from(es)).observeOn(Schedulers.computation()).subscribe(statusCode -> {
            }, error -> {
            }, finishedLatch::countDown);
        }
        finishedLatch.await();
        t = (System.nanoTime() - t) / 1000000; //ms
        System.out.println("RxJavaWithParallel TPS: " + count * 1000 / t);
        es.shutdownNow();
    }
}
