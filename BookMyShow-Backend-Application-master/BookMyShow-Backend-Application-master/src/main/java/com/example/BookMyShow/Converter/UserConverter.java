package com.example.BookMyShow.Converter;

import com.example.BookMyShow.EntryDtos.UserEntryDto;
import com.example.BookMyShow.Models.User;

public class UserConverter {

    public static User convertDtoToEntity(UserEntryDto userEntryDto){
        User user= User.builder().age(userEntryDto.getAge()).mobNo(userEntryDto.getMobNo()).name(userEntryDto.getName())
                .address(userEntryDto.getAddress()).email(userEntryDto.getEmail()).build();
        return user;
    }
}
