package com.example.BookMyShow.EntryDtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TicketEntryDto {

    private int showId;

    private List<String> requestedSeats;

    private int userId;

}
