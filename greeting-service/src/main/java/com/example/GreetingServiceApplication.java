package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(GreetingProperties.class)
public class GreetingServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(GreetingServiceApplication.class, args);
	}
}
