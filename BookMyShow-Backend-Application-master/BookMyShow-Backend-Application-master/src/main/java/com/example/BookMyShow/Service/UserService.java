package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.UserConverter;
import com.example.BookMyShow.EntryDtos.UserEntryDto;
import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto)throws Exception{

       User user= UserConverter.convertDtoToEntity(userEntryDto);
        userRepository.save(user);
        return "user created successfully";
    }
}
