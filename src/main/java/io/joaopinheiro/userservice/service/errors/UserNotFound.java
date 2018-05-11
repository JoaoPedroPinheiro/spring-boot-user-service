package io.joaopinheiro.userservice.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException{

    private final static String USER_NOT_FOUND_MESSAGE = "The Superhero with the following ID was not found : "

    public UserNotFound(Long id){
        super(message);
    }
}
