package com.example;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {

	private static final Logger LOG = Logger.getLogger(NameController.class.getName());

	private NameProperties nameProperties;

	@Autowired
	public NameController(NameProperties nameProperties) {
		this.nameProperties = nameProperties;
	}

	@RequestMapping
	public String getName() {
		LOG.info("Name: " + nameProperties.getName());
		return nameProperties.getName();
	}
}
