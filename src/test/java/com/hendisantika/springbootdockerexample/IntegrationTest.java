package com.hendisantika.springbootdockerexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 8/20/17
 * Time: 5:34 AM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class IntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHome() throws Exception {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + SpringBootDockerExampleApplication.PATH_HOME, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void testEnvironmentVariables() throws Exception {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + SpringBootDockerExampleApplication.PATH_ENVIRONMENT_VARIABLES, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void testSystemProperties() throws Exception {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + this.port + SpringBootDockerExampleApplication.PATH_SYSTEM_PROPERTIES, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
}
