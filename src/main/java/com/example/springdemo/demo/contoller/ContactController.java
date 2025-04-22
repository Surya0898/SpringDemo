package com.example.springdemo.demo.contoller;

import com.example.springdemo.demo.entity.Contact;
import com.example.springdemo.demo.entity.User;
import com.example.springdemo.demo.service.AddressService;
import com.example.springdemo.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAllContacts(){
        return contactService.getAllContacts();
    }

    @GetMapping("/id")
    public Contact getContactById(@PathVariable Long id){
        return contactService.getContactById(id);
    }

    @PostMapping("/user/{id}")
    public Contact createContact(@PathVariable Long id, @RequestBody Contact contact){
        return contactService.createContact(contact,id);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact){
        return contactService.updateContact(id,contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id){
        contactService.deleteContact(id);
    }
}
