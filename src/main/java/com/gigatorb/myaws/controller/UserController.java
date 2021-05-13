package com.gigatorb.myaws.controller;

import com.gigatorb.myaws.entity.User;
import com.gigatorb.myaws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // get all users
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable (value = "id") long userId){
        return userService.getUserById(userId);
    }

    // create user
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createOrUpdateUser(user);
    }

    // update user
    @PutMapping("{id}")
    public User updateUser(@RequestBody User user, @PathVariable (value = "id") long userId){
        User existingUser =
                userService.getUserById(userId);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userService.createOrUpdateUser(existingUser);
    }

    // delete user by id
    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable (value = "id") long userId){
        User existingUser = userService.getUserById(userId);
        userService.deleteUserById(existingUser);
        return ResponseEntity.ok().build();
    }

}
