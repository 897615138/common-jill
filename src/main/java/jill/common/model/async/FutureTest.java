package jill.common.model.async;

import jill.common.constant.NumConstant;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * @author Jill W
 * @date 2020/11/17
 */

public class FutureTest {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(6, new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d")
                //新的线程工厂会创建新的守护进程
                .daemon(true).build());
        Task task = new Task();
        Future<String> submit = executorService.submit(task);
        try {
            //阻塞 完成获得结果 没完成就阻塞(加上时间的参数就会等待指定时间)
            String s = submit.get();
            System.out.println("直接获取" + s);
            String s1 = submit.get(NumConstant.ONE, TimeUnit.SECONDS);
            System.out.println("超时取消获取" + s1);
            //轮询
            boolean done = submit.isDone();
            System.out.println("是否完成" + done);
            boolean cancel = submit.cancel(true);
            System.out.println("是否取消成功" + cancel);
        } catch (TimeoutException | ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
