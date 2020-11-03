package zookeeper.framework.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import zookeeper.framework.kafka.producer.SimpleProducer;

import java.text.MessageFormat;


/**
 * 消息消费者的第一个示例
 */
@Component("simpleConsumer")
public class SimpleConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SimpleProducer.class);

    @KafkaListener(id = "test", topics = {"topic-test1"})
    public void listen(String data) {
        System.out.println("SimpleConsumer收到消息：" + data);
        logger.info(MessageFormat.format("SimpleConsumer收到消息：{0}", data));
    }

}
