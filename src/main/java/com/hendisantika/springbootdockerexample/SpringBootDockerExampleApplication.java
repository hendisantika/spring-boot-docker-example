package com.hendisantika.springbootdockerexample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@SpringBootApplication
@RestController
public class SpringBootDockerExampleApplication {

	static final String PATH_HOME = "/";
	static final String PATH_DERP = "/derp";
	static final String PATH_SYSTEM_PROPERTIES = "/_/properties";
	static final String PATH_ENVIRONMENT_VARIABLES = "/_/environment";

	@Value("${com.hendisantika.springbootdockerexample}")
	private String derp;

	@RequestMapping(produces = "text/plain", path = PATH_HOME)
	public String home() {
		return "Hello Docker World";
	}

	@RequestMapping(produces = "text/plain", path = PATH_DERP)
	public String derp() {
		return derp;
	}

	@RequestMapping(produces = "text/plain", path = PATH_SYSTEM_PROPERTIES)
	public String systemProperties() {
		final Map<String,String> sortedProperties = new TreeMap<>();

		for (final String property : System.getProperties().stringPropertyNames()) {
			sortedProperties.put(property, System.getProperty(property));
		}

		final StringBuilder buf = new StringBuilder();

		for (final Map.Entry<String,String> entry : sortedProperties.entrySet()) {
			buf.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\r\n");
		}

		return buf.toString();
	}

	@RequestMapping(produces = "text/plain", path = PATH_ENVIRONMENT_VARIABLES)
	public String environmentVariables() {
		final Map<String,String> sortedEnvironmentVariables = new TreeMap<>(System.getenv());

		final StringBuilder buf = new StringBuilder();

		for (final Map.Entry<String,String> entry : sortedEnvironmentVariables.entrySet()) {
			buf.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\r\n");
		}

		return buf.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDockerExampleApplication.class, args);
	}
}
