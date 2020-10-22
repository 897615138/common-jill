package zookeeper.commonzookeeper.kafka.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zookeeper.commonzookeeper.kafka.model.DemoObj;
import zookeeper.commonzookeeper.kafka.producer.SimpleProducer;

import javax.annotation.Resource;


@RestController
@RequestMapping("/kafka")
public class TestKafkaController {
	@Resource(name="simpleProducer")
	private SimpleProducer producer;
	
	private final String TOPIC = "topic-test"; //测试使用topic
	private final String TOPIC2 = "topic-test2"; //测试使用topic
	
	@RequestMapping("/send")
	public String send(String data){
		producer.sendMessage(TOPIC, data);
		
		return "发送数据【" + data + "】成功！";
	}
	
	@RequestMapping("/send2")
	public String send2(DemoObj demoObj){
		producer.sendObjectMessage(TOPIC2, demoObj);
		
		return "发送数据【" + demoObj + "】成功！";
	}
	
}
