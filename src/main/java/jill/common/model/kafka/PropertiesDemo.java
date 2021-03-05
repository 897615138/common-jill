package jill.common.model.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author Jill W
 * @date 2020/12/24
 */
public class PropertiesDemo {
    private static final Properties providerProps = new Properties();
    private static final Properties consumerProps = new Properties();
    private static final String GROUP_ID = "5";

    static {
        providerProps.put("bootstrap.servers", "127.0.0.1:9092");
        /*
        request.required.acks
        0表示producer毋须等待leader的确认
        1代表需要leader确认写入它的本地log并立即确认
        -1代表所有的备份都完成后确认。 仅仅for sync
         */
        providerProps.put("acks", "all");
        providerProps.put("retries", 0);
        providerProps.put("batch.size", 16384);
        providerProps.put("key.serializer", StringSerializer.class.getName());
        providerProps.put("value.serializer", StringSerializer.class.getName());

        consumerProps.put("bootstrap.servers", "127.0.0.1:9092");
        consumerProps.put("group.id", GROUP_ID);
        consumerProps.put("enable.auto.commit", "true");
        consumerProps.put("auto.commit.interval.ms", "1000");
        consumerProps.put("session.timeout.ms", "30000");
        consumerProps.put("max.poll.records", 1000);
        consumerProps.put("auto.offset.reset", "earliest");
        consumerProps.put("key.deserializer", StringDeserializer.class.getName());
        consumerProps.put("value.deserializer", StringDeserializer.class.getName());
    }

    public static Properties getProviderProperties() {
        return providerProps;
    }

    public static Properties getConsumerProperties() {
        return consumerProps;
    }
}
