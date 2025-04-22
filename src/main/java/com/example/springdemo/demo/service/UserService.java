package com.example.springdemo.demo.service;

import com.example.springdemo.demo.dto.UserDTO;
import com.example.springdemo.demo.entity.User;
import com.example.springdemo.demo.exception.UserNotFoundException;
import com.example.springdemo.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private Map<Integer,User> userMap = new HashMap<>();

    public List<UserDTO> getAllUser(){
        log.info("Calling get All user");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        log.info("Calling get user by ID");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return modelMapper.map(user, UserDTO.class);
    }


    public UserDTO createUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO,User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDTO.class);
    }

    public User updateUser(Long id, User updatedUser){
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        existingUser.setName(updatedUser.getName());
        existingUser.setAge(updatedUser.getAge());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
