package com.example.springdemo.demo.dto;

public class ContactDTO {

    private int id;

    private String phone;
    private String email;
    private String type;

    public ContactDTO() {
    }

    public ContactDTO(int id, String phone, String email, String type) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
