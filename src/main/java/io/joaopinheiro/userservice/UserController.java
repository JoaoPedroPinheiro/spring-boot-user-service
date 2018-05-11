package io.joaopinheiro.userservice;


import io.joaopinheiro.userservice.user.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping(path = "users", produces = "application/json")
    public User getUser(){
        //TODO
        return null;
    }

    @PostMapping(path= "users", produces = "application/json", consumes = "application/json")
    public User createUser(@RequestBody User user){
        //TODO
        return null;
    }

    @GetMapping(path="users/{id}", produces = "application/json")
    public User getUser(@PathVariable("id") Long id){
        //TODO
        return null;
    }

    @PutMapping(path= "users/{id}", produces = "application/json")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long id){
        //TODO
        return null;
    }

    @DeleteMapping(path ="users/{id}", produces = "application/json")
    public User deleteUser(@RequestBody User user, @PathVariable("id") Long id){
        //TODO
        return null;
    }

}