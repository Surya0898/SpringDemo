package com.example.springdemo.demo.service;

import com.example.springdemo.demo.entity.Contact;
import com.example.springdemo.demo.entity.User;
import com.example.springdemo.demo.exception.ContactNotFoundException;
import com.example.springdemo.demo.repository.ContactRepository;
import com.example.springdemo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final UserRepository userRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository, UserRepository userRepository){
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id){
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with ID: " + id));
    }

    public Contact createContact(Contact contact, Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with ID: " + userId));
        contact.setUser(user);
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, Contact contact){
        Contact existing = getContactById(id);
        existing.setType(contact.getType());
        existing.setPhone(contact.getPhone());
        existing.setEmail(contact.getEmail());
        return contactRepository.save(existing);
    }
    public void deleteContact(Long id){
        contactRepository.deleteById(id);
    }
}
