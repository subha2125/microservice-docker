package employee.kafka.consumer.listner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employee.kafka.consumer.model.EmployeeDetails;

@RestController
@RequestMapping("/empkafka")
public class KafkaListner {
	
	Logger logger = LoggerFactory.getLogger(KafkaListner.class);
	
	List<EmployeeDetails> userDetails = null;
	
	String kafkaMessage;
	
	@Value("${kafka.topic}")
    private String topic;
	
	@GetMapping("/userDeatails")
	public List<EmployeeDetails> consumeUserDeatails() {
		return userDetails;
	}
	
	@GetMapping("/userMessage")
	public String consumeMessage() {
		return kafkaMessage;
	}

	@KafkaListener(topics = "Kafka_Employee", groupId = "group_json",
            containerFactory = "empKafkaListenerFactory")
    public List<EmployeeDetails> consumeJson(List<EmployeeDetails> details) {
		logger.info("Consumed Details Message {}: " , details);
		userDetails = details;
		return userDetails;
    }
	
	
	@KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume(String message) {
		kafkaMessage = message;
       logger.info("Consumed Details Message {}: " , kafkaMessage);
    }
}
