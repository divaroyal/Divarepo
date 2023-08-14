package com.example.BookMyShow.Converter;

import com.example.BookMyShow.EntryDtos.TicketEntryDto;
import com.example.BookMyShow.Models.Ticket;

public class TicketConverter {
    public static Ticket convertDtoToEntity(TicketEntryDto ticketEntryDto){
        Ticket ticket=Ticket.builder().build();
        return ticket;
    }
}
