import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @title: UUIDTest
 * @Author shannian
 * @Date: 2021/3/25
 */
@Slf4j
public class UUIDTest {


    @Test
    public void testUuidGenerateOnConcurrency() throws InterruptedException {

        List<String> uuidList = new CopyOnWriteArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            executor.execute(new UuidGenerateTask(countDownLatch, uuidList));
        }
        countDownLatch.await();

        Map<String, Integer> uuidCountMap = Maps.newHashMap();
        uuidList.stream().forEach(uuid -> {
            Integer increaseCount = uuidCountMap.containsKey(uuid) ? uuidCountMap.get(uuid) + 1 : 0;
            uuidCountMap.put(uuid, increaseCount);
        });
        uuidCountMap.entrySet().stream().filter(entry -> entry.getValue() > 1).forEach(entry -> {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        });
        executor.shutdown();
    }

    private static class UuidGenerateTask implements Runnable {

        private final CountDownLatch countDownLatch;

        private final List<String> uuidList;

        public UuidGenerateTask(CountDownLatch countDownLatch, List<String> uuidList) {
            this.countDownLatch = countDownLatch;
            this.uuidList = uuidList;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                String uuid = UUID.randomUUID().toString();
                uuidList.add(uuid);
                log.info(uuid);
            }
            countDownLatch.countDown();
        }
    }


}
