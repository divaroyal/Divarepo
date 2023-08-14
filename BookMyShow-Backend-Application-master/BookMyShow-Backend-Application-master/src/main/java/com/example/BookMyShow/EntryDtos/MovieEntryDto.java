package com.example.BookMyShow.EntryDtos;

import com.example.BookMyShow.Genres.MovieGenre;
import com.example.BookMyShow.Genres.MovieLanguage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieEntryDto {

    private String movieName;

    private MovieGenre genre;

    private MovieLanguage language;

    private double rating;

    private int duration;

}
