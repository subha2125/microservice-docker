package employee.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableEurekaClient
public class EmployeeDetailsKafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDetailsKafkaConsumerApplication.class, args);
	}

}
