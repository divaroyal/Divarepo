package com.example.BookMyShow.Converter;

import com.example.BookMyShow.EntryDtos.MovieEntryDto;

import com.example.BookMyShow.Models.Movie;


public class MovieConverter {
    public static Movie convertDtoToEntity(MovieEntryDto movieEntryDto){

      Movie movie=Movie.builder().movieName(movieEntryDto.getMovieName()).genre(movieEntryDto.getGenre()).rating(movieEntryDto.getRating())
              .duration(movieEntryDto.getDuration()).language(movieEntryDto.getLanguage()).build();
      return movie;
    }
}
