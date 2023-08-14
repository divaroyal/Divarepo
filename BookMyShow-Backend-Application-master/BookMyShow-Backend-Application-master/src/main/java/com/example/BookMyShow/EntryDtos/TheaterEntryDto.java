package com.example.BookMyShow.EntryDtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TheaterEntryDto {
    private String name;

    private String location;

    private int classicSeatCount;

    private int premiumSeatCount;
}
