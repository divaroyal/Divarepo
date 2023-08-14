package com.example.BookMyShow.Models;


import com.example.BookMyShow.Genres.MovieGenre;
import com.example.BookMyShow.Genres.MovieLanguage;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String movieName;

    @Enumerated(value = EnumType.STRING)
    private MovieGenre genre;

    @Enumerated(value = EnumType.STRING)
    private MovieLanguage language;

    private double rating;

    private int duration;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show>showList=new ArrayList<>();
}
