package com.example;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	private static final Logger log = Logger.getLogger(GreetingController.class.getName());

	private final GreetingProperties greetingProperties;

	@Autowired
	public GreetingController(GreetingProperties greetingProperties) {
		this.greetingProperties = greetingProperties;
	}

	@RequestMapping("/")
	public String getGreeting() {
		log.info("Greeting: " + this.greetingProperties.getGreeting());
		return this.greetingProperties.getGreeting();
	}

	@RequestMapping("/{languageCode}")
	public String getGreeting(@PathVariable String languageCode) {
		log.info("Language Code: " + languageCode);
		log.info("Greetings: " + this.greetingProperties.getGreetings());
		log.info("Greeting: " + this.greetingProperties.getGreetings().get(languageCode.toUpperCase()));
		return this.greetingProperties.getGreetings().get(languageCode.toUpperCase());
	}

}
