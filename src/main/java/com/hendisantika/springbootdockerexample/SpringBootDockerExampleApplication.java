package com.hendisantika.springbootdockerexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootDockerExampleApplication {

	static final String PATH_HOME = "/";
	static final String PATH_DERP = "/derp";
	static final String PATH_SYSTEM_PROPERTIES = "/_/properties";
	static final String PATH_ENVIRONMENT_VARIABLES = "/_/environment";

	@Value("${io.dweomer.example.derp}")
	private String derp;

	@RequestMapping(produces = "text/plain", path = PATH_HOME)
	public String home() {
		return "Hello Docker World";
	}

	@RequestMapping(produces = "text/plain", path = PATH_DERP)
	public String derp() {
		return derp;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDockerExampleApplication.class, args);
	}
}
