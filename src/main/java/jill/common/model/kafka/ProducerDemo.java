package jill.common.model.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author Jill W
 * @date 2020/12/24
 */
public class ProducerDemo implements Runnable {
    public static KafkaProducer<String, String> producer;

    static {
        producer = new KafkaProducer<>(PropertiesDemo.getProviderProperties());
    }

    private final String topic;

    public ProducerDemo(String topic) {
        this.topic = topic;
    }

    public static void main(String[] args) {
        ProducerDemo test = new ProducerDemo("KAFKA_TEST");
        Thread thread = new Thread(test);
        thread.start();
    }

    public void run() {
        int messageNo = 1;
        try {
            for (; ; ) {
                String messageStr = "你好，这是第" + messageNo + "条数据";
                producer.send(new ProducerRecord<>(topic, "testKey", "testValue"));
                //生产了100条就打印
                if (messageNo % 100 == 0) {
                    System.out.println("发送的信息:" + messageStr);
                }
                //生产1000条就退出
                if (messageNo % 1000 == 0) {
                    System.out.println("成功发送了" + messageNo + "条");
                    break;
                }
                messageNo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
