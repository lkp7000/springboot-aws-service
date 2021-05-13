package com.gigatorb.myaws.service;

import com.gigatorb.myaws.entity.User;
import com.gigatorb.myaws.exception.ResourceNotFoundException;
import com.gigatorb.myaws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long userId){
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: "+userId));
    }

    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(User existingUser) {
        userRepository.delete(existingUser);
    }
}
