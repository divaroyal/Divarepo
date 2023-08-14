package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.ShowConverter;
import com.example.BookMyShow.EntryDtos.ShowEntryDto;
import com.example.BookMyShow.Genres.SeatType;
import com.example.BookMyShow.Models.*;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    public String addShow(ShowEntryDto showEntryDto)throws Exception{
        Show show= ShowConverter.convertDtoToEntity(showEntryDto);

        Movie movie=movieRepository.findById(showEntryDto.getMovieId()).get();

        Theater theater=theaterRepository.findById(showEntryDto.getTheaterId()).get();

        show.setMovie(movie);
        show.setTheater(theater);

        List<ShowSeat>showSeatList=createShowSeat(showEntryDto,show);
        show.setShowSeatList(showSeatList);

        show=showRepository.save(show);
        movie.getShowList().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "show added successfully";

    }

    public List<ShowSeat> createShowSeat(ShowEntryDto showEntryDto,Show show){

        Theater theater=show.getTheater();
        List<TheaterSeats>theaterSeatsList=theater.getTheaterSeatsList();
        List<ShowSeat>showSeatList=new ArrayList<>();

        for(TheaterSeats theaterSeats:theaterSeatsList){
            ShowSeat showSeat=new ShowSeat();
            showSeat.setSeatNo(theaterSeats.getSeatNo());
            showSeat.setSeatType(theaterSeats.getSeatType());

            if(theaterSeats.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setSeatPrice(showEntryDto.getClassicSeatPrice());
            else
                showSeat.setSeatPrice(showEntryDto.getPremiumSeatPrice());
            showSeat.setShow(show);
            showSeat.setBooked(false);
            showSeatList.add(showSeat);

        }
        return showSeatList;
    }
}
