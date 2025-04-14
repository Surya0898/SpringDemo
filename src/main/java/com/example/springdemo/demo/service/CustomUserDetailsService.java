package com.example.springdemo.demo.service;

import com.example.springdemo.demo.entity.User;
import com.example.springdemo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("User found: " + user);
        System.out.println("Role from DB: " + user.getRole());
        System.out.println("Loading user: " + username);


        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole())
        );

        System.out.println("Authorities: " + authorities);

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }
}
