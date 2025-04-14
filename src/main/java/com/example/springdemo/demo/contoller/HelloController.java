package com.example.springdemo.demo.contoller;

import com.example.springdemo.demo.service.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    private final Quote quote;

    @Autowired
    public HelloController(Quote quote){
        this.quote = quote;
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello! Springboot";
    }

    @GetMapping("/helloworld")
    public String sayHelloWorld(){
        return "Hello World!";
    }

//    @GetMapping("/user")
//    public String getUser(){
//        return "Getting user details";
//    }
//
//    @PostMapping("/user")
//    public String createUser(){
//        return "Creating User";
//    }
//
//    @PutMapping("/user")
//    public String updateUser(){
//        return "Updating user";
//    }
//
//    @DeleteMapping("/user")
//    public String deleteUser(){
//        return "Deleting user";
//    }

    @GetMapping("/quote")
    public String getQuote(){

        return quote.lifeQuote();
    }
}
