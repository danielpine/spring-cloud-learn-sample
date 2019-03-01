package com.example;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties()
public class GreetingProperties {
	String greeting;
	Map<String, String> greetings;

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public Map<String, String> getGreetings() {
		return greetings;
	}

	public void setGreetings(Map<String, String> greetings) {
		this.greetings = greetings;
	}

}
