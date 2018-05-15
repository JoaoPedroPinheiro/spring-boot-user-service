package io.joaopinheiro.userservice.integration;


import io.joaopinheiro.userservice.User;
import io.joaopinheiro.userservice.UserBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTests{

    private final String BASE_URL = "http://localhost:8080/users/";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void canCreateUser(){
        User user = new UserBuilder().build();

        assertEquals(HttpStatus.NOT_FOUND, this.restTemplate.getForEntity(BASE_URL+user.getId(),User.class).getStatusCode());
        assertEquals(HttpStatus.CREATED, this.restTemplate.postForEntity(BASE_URL, user, User.class).getStatusCode());
        assertEquals(HttpStatus.OK, this.restTemplate.getForEntity(BASE_URL+user.getId(),User.class).getStatusCode());

    }

}
