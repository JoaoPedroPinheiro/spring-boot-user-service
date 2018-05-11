package io.joaopinheiro.userservice.service;

import io.joaopinheiro.userservice.repository.UserRepository;
import io.joaopinheiro.userservice.service.errors.UserNotFound;
import io.joaopinheiro.userservice.user.User;
import org.springframework.beans.factory.annotation.Autowired;

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

    public User createUser(User user) {
        //TODO: If user already exists, throw error. Otherwise save it to DB and return
        return null;
    }

    public User updateUser(User user){
        //TODO: If user exists update. Otherwise throw error and return
        return null;
    }

    public User deleteUser(Long id){
        repository.deleteById(id);
    }
}
