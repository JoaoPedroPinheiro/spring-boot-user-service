package io.joaopinheiro.userservice;


import io.joaopinheiro.userservice.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping("userservice")
    public User getUser(){

    }

}
