package com.example.springdemo.demo.service;

import com.example.springdemo.demo.entity.Address;
import com.example.springdemo.demo.entity.User;
import com.example.springdemo.demo.repository.AddressRepository;
import com.example.springdemo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository){
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id){
        return addressRepository.findById(id).orElseThrow(()-> new RuntimeException("Address not found with:"+id));
    }

    public Address createAddress(Address address, int userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with ID: " + userId));
        address.setUser(user);
        return addressRepository.save(address);
    }
    public Address updateAddress(Long id, Address updatedAddress) {
        Address existing = getAddressById(id);
        existing.setStreet(updatedAddress.getStreet());
        existing.setCity(updatedAddress.getCity());
        existing.setState(updatedAddress.getState());
        existing.setZipcode(updatedAddress.getZipcode());
        return addressRepository.save(existing);
    }
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
