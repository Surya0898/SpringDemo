package com.example.springdemo.demo.service;

import com.example.springdemo.demo.entity.User;
import com.example.springdemo.demo.exception.UserNotFoundException;
import com.example.springdemo.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private Map<Integer,User> userMap = new HashMap<>();

    public List<User> getAllUser(){
        log.info("Calling get All user");
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        log.info("Calling get user by ID");
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }


    public User createUser(User user){
        System.out.println(user);
        return userRepository.save(user);
    }

    public User updateUser(int id, User updatedUser){
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        existingUser.setName(updatedUser.getName());
        existingUser.setAge(updatedUser.getAge());

        return userRepository.save(existingUser);
    }

    public void deleteUser(int id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
