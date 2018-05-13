package io.joaopinheiro.userservice.service;

import io.joaopinheiro.userservice.repository.UserRepository;
import io.joaopinheiro.userservice.service.errors.UserAlreadyExists;
import io.joaopinheiro.userservice.service.errors.UserNotFound;
import io.joaopinheiro.userservice.service.errors.UserUpdateError;
import io.joaopinheiro.userservice.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repo){
        this.userRepository = repo;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserByID(Long id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFound(id));
    }

    public User createUser(User user) {
        if(user.getId() != null && userRepository.findById(user.getId()).isPresent()) {
            throw new UserAlreadyExists(user.getId());
        }

        User result = userRepository.save(user);
        return result;
    }

    public User updateUser(User user, Long id){
        userRepository.findById(id).orElseThrow(()-> new UserNotFound(id));
        if(user.getId() != null && !user.getId().equals(id))
            throw new UserUpdateError();

        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.findById(id).orElseThrow(()-> new UserNotFound(id));
        userRepository.deleteById(id);
    }
}
