package io.joaopinheiro.userservice.service;

import io.joaopinheiro.userservice.repository.UserRepository;
import io.joaopinheiro.userservice.service.errors.UserAlreadyExists;
import io.joaopinheiro.userservice.service.errors.UserNotFound;
import io.joaopinheiro.userservice.service.errors.UserUpdateError;
import io.joaopinheiro.userservice.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> getAll(){
        return repository.findAll();
    }

    public User getUserByID(Long id){
        return repository.findById(id).orElseThrow(()-> new UserNotFound(id));
    }

    public URI createUser(User user) {
        if(user.getId() != null && repository.findById(user.getId()).isPresent()) {
            throw new UserAlreadyExists(user.getId());
        }

        User result = repository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return location;
    }

    public User updateUser(User user, Long id){
        repository.findById(id).orElseThrow(()-> new UserNotFound(id));
        if(user.getId() != null && !user.getId().equals(id))
            throw new UserUpdateError();

        return repository.save(user);
    }

    public void deleteUser(Long id){
        repository.findById(id).orElseThrow(()-> new UserNotFound(id));
        repository.deleteById(id);
    }
}
