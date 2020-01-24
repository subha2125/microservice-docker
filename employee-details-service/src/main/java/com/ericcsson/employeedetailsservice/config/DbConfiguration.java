package com.ericcsson.employeedetailsservice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfiguration {
	/*@Value("${db_url}")
	private String url;
	@Value("${db_username}")
	private String userName;
	@Value("${db_password}")
	private String password;

	@Bean
	public DataSource getInstance() {
		DataSourceBuilder builder = DataSourceBuilder.create();
		builder.url(url);
		builder.username(userName);
		builder.password(password);
		return builder.build();
	}*/

}
