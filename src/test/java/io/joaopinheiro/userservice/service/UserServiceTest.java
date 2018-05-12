package io.joaopinheiro.userservice.service;

import io.joaopinheiro.userservice.repository.UserRepository;
import io.joaopinheiro.userservice.service.errors.UserNotFound;
import io.joaopinheiro.userservice.user.User;
import io.joaopinheiro.userservice.user.UserBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService service;

    @Before
    public void before(){
        userRepository = mock(UserRepository.class);
        service = new UserService(userRepository);
    }


    @Test
    public void getUserByID() {
        User result = new UserBuilder().build();

        given(userRepository.findById(result.getId())).willReturn(Optional.of(result));
        assertEquals(result, service.getUserByID(result.getId()));
    }

    @Test(expected = UserNotFound.class)
    public void getUserByIDNotFound(){
        given(userRepository.findById(Long.MAX_VALUE)).willReturn(Optional.empty());
        service.getUserByID(Long.MAX_VALUE);
    }

    @Test
    public void createUser() {

    }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUser() {
    }
}