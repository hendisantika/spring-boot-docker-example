package com.hendisantika.springbootdockerexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDockerExampleApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testHome() {
		assertEquals("Hello Docker World", new SpringBootDockerExampleApplication().home());
	}

	@Test
	public void testEnvironmentVariables() {
		assertNotNull(new SpringBootDockerExampleApplication().environmentVariables());
	}

	@Test
	public void testSystemProperties() {
		assertNotNull(new SpringBootDockerExampleApplication().systemProperties());
	}
}
