package com.example.springdemo.demo.repository;

import com.example.springdemo.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
