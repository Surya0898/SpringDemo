package com.example.springdemo.demo.contoller;

import com.example.springdemo.demo.entity.User;
import com.example.springdemo.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){
        User updated = userService.updateUser(id, user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
