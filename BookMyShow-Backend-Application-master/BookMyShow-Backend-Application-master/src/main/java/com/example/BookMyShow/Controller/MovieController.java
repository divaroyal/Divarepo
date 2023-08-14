package com.example.BookMyShow.Controller;

import com.example.BookMyShow.EntryDtos.MovieEntryDto;
import com.example.BookMyShow.Models.Movie;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add_movie")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){
        try {
            String response=movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            String response="unable to add movie";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get_movie")
    public ResponseEntity<Movie> getMovie(int movieId) throws Exception {
        try {
            Movie movie=movieService.getMovieById(movieId);
            return new ResponseEntity<>(movie,HttpStatus.FOUND);
        }
        catch (Exception e){
            throw new Exception("Unable to find movie");
        }
    }
}
