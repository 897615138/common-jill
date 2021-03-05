package jill.common.model.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;

/**
 * @author Jill W
 * @date 2020/12/24
 */
public class ConsumerDemo implements Runnable {
    private final KafkaConsumer<String, String> consumer;

    public ConsumerDemo(String topic) {
        this.consumer = new KafkaConsumer<>(PropertiesDemo.getConsumerProperties());
        this.consumer.subscribe(Collections.singletonList(topic));
    }

    public static void main(String[] args) {
        ConsumerDemo test1 = new ConsumerDemo("KAFKA_TEST");
        Thread thread1 = new Thread(test1);
        thread1.start();
    }

    @Override
    public void run() {
        int messageNo = 1;
        System.out.println("---------开始消费---------");
        try {
            for (; ; ) {
                ConsumerRecords<String, String> msgList = consumer.poll(Duration.ofSeconds(1));
                if (null != msgList && msgList.count() > 0) {
                    for (ConsumerRecord<String, String> record : msgList) {
                        //消费100条就打印 ,但打印的数据不一定是这个规律的
                        if (messageNo % 100 == 0) {
                            System.out.println(messageNo + "=======receive: key = " + record.key() + ", value = " + record.value() + " offset===" + record.offset());
                        }
                        //当消费了1000条就退出
                        if (messageNo % 1000 == 0) {
                            break;
                        }
                        messageNo++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
