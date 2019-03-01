package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(NameProperties.class)
public class NameServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NameServiceApplication.class, args);
	}

}
