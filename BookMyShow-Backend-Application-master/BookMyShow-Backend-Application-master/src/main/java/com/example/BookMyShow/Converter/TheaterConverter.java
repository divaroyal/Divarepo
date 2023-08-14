package com.example.BookMyShow.Converter;

import com.example.BookMyShow.EntryDtos.TheaterEntryDto;
import com.example.BookMyShow.Models.Theater;

public class TheaterConverter {
    public static Theater convertDtoToEntity(TheaterEntryDto theaterEntryDto){
        Theater theater=Theater.builder().name(theaterEntryDto.getName()).location(theaterEntryDto.getLocation()).build();
        return theater;
    }
}
