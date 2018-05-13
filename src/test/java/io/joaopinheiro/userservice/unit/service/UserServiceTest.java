package io.joaopinheiro.userservice.unit.service;

import io.joaopinheiro.userservice.repository.UserRepository;
import io.joaopinheiro.userservice.service.UserService;
import io.joaopinheiro.userservice.service.errors.UserAlreadyExists;
import io.joaopinheiro.userservice.service.errors.UserNotFound;
import io.joaopinheiro.userservice.service.errors.UserUpdateError;
import io.joaopinheiro.userservice.user.User;
import io.joaopinheiro.userservice.UserBuilder;
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

    @Test (expected = UserAlreadyExists.class)
    public void createUserAlreadyExists() {
        User user = new UserBuilder().build();
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        service.createUser(user);
    }

    @Test
    public void createUser(){
        User user = new UserBuilder().build();
        given(userRepository.findById(user.getId())).willReturn(Optional.empty());
        given(userRepository.save(user)).willReturn(user);

        assertEquals(user, service.createUser(user));
    }

    @Test (expected = UserNotFound.class)
    public void updateUserNotFound() {
        User user = new UserBuilder().build();
        given(userRepository.findById(user.getId())).willReturn((Optional.empty()));
        service.updateUser(user, user.getId());
    }

    @Test (expected = UserUpdateError.class)
    public void updateUserIdUpdate(){
        User user = new UserBuilder().build();
        given(userRepository.findById(Long.MAX_VALUE)).willReturn(Optional.of(user));
        service.updateUser(user, Long.MAX_VALUE);
    }

    @Test
    public void updateUser(){
        User oldUser = new UserBuilder().build();
        User newUser = new UserBuilder()
                .withEmail("updated@email.com")
                .withUsername("Updated Name").build();

        given(userRepository.findById(oldUser.getId())).willReturn(Optional.of(oldUser));
        given(userRepository.save(newUser)).willReturn(newUser);

        assertEquals(newUser, service.updateUser(newUser, oldUser.getId()));
    }

    @Test (expected = UserNotFound.class)
    public void deleteUserNotFound() {
        given(userRepository.findById(Long.MAX_VALUE)).willReturn(Optional.empty());
        service.deleteUser(Long.MAX_VALUE);
    }


}