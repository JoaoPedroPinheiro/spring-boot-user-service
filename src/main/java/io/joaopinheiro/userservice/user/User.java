package io.joaopinheiro.userservice.user;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;

    public User(Long id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        } else if(obj instanceof User) {
            User user = (User) obj;
            return this.email.equals(user.email) &&
                    this.username.equals(user.username) &&
                    this.id.equals(user.id);
        }

        return false;
    }
}
