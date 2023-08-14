package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.TheaterConverter;
import com.example.BookMyShow.EntryDtos.TheaterEntryDto;
import com.example.BookMyShow.Genres.SeatType;
import com.example.BookMyShow.Models.Theater;
import com.example.BookMyShow.Models.TheaterSeats;
import com.example.BookMyShow.Repository.TheaterRepository;
import com.example.BookMyShow.Repository.TheaterSeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    TheaterSeatsRepository theaterSeatsRepository;
    public String addTheater(TheaterEntryDto theaterEntryDto)throws Exception{
        Theater theater= TheaterConverter.convertDtoToEntity(theaterEntryDto);
        List<TheaterSeats>theaterSeatsList=createTheaterSeats(theaterEntryDto,theater);
        theater.setTheaterSeatsList(theaterSeatsList);
        theaterRepository.save(theater);
        return "theater added successfully";
    }
    private List<TheaterSeats> createTheaterSeats(TheaterEntryDto theaterEntryDto,Theater theater){
        int classicSeats=theaterEntryDto.getClassicSeatCount();
        int premiumSeats=theaterEntryDto.getPremiumSeatCount();
        List<TheaterSeats>theaterSeatsList=new ArrayList<>();
        for(int count=1;count<=classicSeats;count++){
            TheaterSeats theaterSeats=TheaterSeats.builder().seatNo(count+"C").seatType(SeatType.CLASSIC)
                    .theater(theater).build();
            theaterSeatsList.add(theaterSeats);
        }
        for(int count=1;count<=premiumSeats;count++){
            TheaterSeats theaterSeats=TheaterSeats.builder().seatNo(count+"P").seatType(SeatType.PREMIUM)
                    .theater(theater).build();
            theaterSeatsList.add(theaterSeats);
        }

        return theaterSeatsList;
    }
}
