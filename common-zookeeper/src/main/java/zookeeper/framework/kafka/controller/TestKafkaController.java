package zookeeper.framework.kafka.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zookeeper.framework.kafka.model.DemoObj;
import zookeeper.framework.kafka.producer.SimpleProducer;

import javax.annotation.Resource;


@RestController
@RequestMapping("/kafka")
public class TestKafkaController {
    @Resource(name = "simpleProducer")
    private SimpleProducer producer;

    @RequestMapping("/send/text")
    public String send(String data) {
        //测试使用topic
        String TOPIC = "topic-test1";
        producer.sendMessage(TOPIC, data);

        return "发送数据【" + data + "】成功！Topic:" + TOPIC;
    }

    @RequestMapping("/send/object")
    public String send2(DemoObj demoObj) {
        //测试使用topic
        String TOPIC = "topic-test2";
        producer.sendObjectMessage(TOPIC, demoObj);

        return "发送数据【" + demoObj + "】成功！Topic:" + TOPIC;
    }

}
