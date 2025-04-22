package com.example.springdemo.demo.config;

import com.example.springdemo.demo.dto.AddressDTO;
import com.example.springdemo.demo.dto.ContactDTO;
import com.example.springdemo.demo.dto.UserDTO;
import com.example.springdemo.demo.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();

        Type addressListType = new TypeToken<List<AddressDTO>>() {}.getType();
        Type contactListType = new TypeToken<List<ContactDTO>>() {}.getType();


        mapper.createTypeMap(User.class, UserDTO.class).addMappings(mapperConfig -> {
            mapperConfig.map(User::getAddresses, UserDTO::setAddresses);
            mapperConfig.map(User::getContacts, UserDTO::setContacts);
        });

        return mapper;
    }
}
