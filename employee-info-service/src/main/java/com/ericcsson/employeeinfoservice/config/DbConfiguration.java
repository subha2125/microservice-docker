package com.ericcsson.employeeinfoservice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfiguration {

	/*@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;

	@Bean
	public DataSource getInstance() {
		DataSourceBuilder builder = DataSourceBuilder.create();
		builder.url(url);
		builder.username(userName);
		builder.password(password);
		builder.driverClassName(driverClass);
		return builder.build();
	}*/
}
