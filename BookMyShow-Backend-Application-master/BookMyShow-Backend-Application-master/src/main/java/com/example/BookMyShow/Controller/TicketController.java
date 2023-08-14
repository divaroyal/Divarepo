package com.example.BookMyShow.Controller;

import com.example.BookMyShow.EntryDtos.TicketEntryDto;
import com.example.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;
    @PostMapping("/add_ticket")
    public ResponseEntity<String> addTicket(@RequestBody TicketEntryDto ticketEntryDto){
        try {
            String response=ticketService.addTicket(ticketEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            String response="unable to add ticket";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
