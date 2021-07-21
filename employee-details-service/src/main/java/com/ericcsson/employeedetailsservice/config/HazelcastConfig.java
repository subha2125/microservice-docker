package com.ericcsson.employeedetailsservice.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ericcsson.employeedetailsservice.model.EmployeeDetails;
import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class HazelcastConfig {

	@Bean
	public Config hazelCastConfig() {
		return new Config().setManagementCenterConfig(
				new ManagementCenterConfig().setEnabled(true).setUrl("http://localhost:8080/hazelcast-mancenter"));

	}

	@Bean
	public HazelcastInstance hazelcastInstance(Config hazelCastConfig) {
		return Hazelcast.newHazelcastInstance(hazelCastConfig);
	}

	@Bean
	public Map<Integer, EmployeeDetails> empDetailsMap(HazelcastInstance hazelcastInstance) {
		return hazelcastInstance.getMap("empDetailsMap");
	}
}
