package com.example.springdemo.demo.repository;

import com.example.springdemo.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
