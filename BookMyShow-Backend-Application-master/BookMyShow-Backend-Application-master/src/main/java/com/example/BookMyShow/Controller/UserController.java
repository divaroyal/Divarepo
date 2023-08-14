package com.example.BookMyShow.Controller;

import com.example.BookMyShow.EntryDtos.UserEntryDto;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add_user")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto){

        try {
          String response= userService.addUser(userEntryDto);
          return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
           String response="unable to add user";
           return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

    }

}
