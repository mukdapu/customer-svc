package com.demo.customer.controller;

import com.demo.customer.CustomerApplication;
import com.demo.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CustomerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "eureka.client.enabled:false")
@ActiveProfiles("test")
public abstract class BaseControllerIT {
    @Autowired
    protected CustomerRepository customerRepository;
    @LocalServerPort
    private int serverPort;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        // initial cleanup
        customerRepository.deleteAll();
    }

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + serverPort + uri;
    }

    protected TestRestTemplate getTestRestTemplate() {
        return testRestTemplate;
    }
}
