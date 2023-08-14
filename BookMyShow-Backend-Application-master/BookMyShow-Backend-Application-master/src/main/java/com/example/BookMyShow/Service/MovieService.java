package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.MovieConverter;
import com.example.BookMyShow.EntryDtos.MovieEntryDto;
import com.example.BookMyShow.Models.Movie;
import com.example.BookMyShow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public String addMovie(MovieEntryDto movieEntryDto)throws Exception{
        Movie movie= MovieConverter.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movie);
        return "movie added successfully";
    }
    public Movie getMovieById(int movieId)throws Exception{
        return movieRepository.findById(movieId).get();
    }
}
