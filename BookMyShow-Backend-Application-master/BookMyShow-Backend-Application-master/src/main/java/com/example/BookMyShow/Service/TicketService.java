package com.example.BookMyShow.Service;

import com.example.BookMyShow.Converter.TicketConverter;
import com.example.BookMyShow.EntryDtos.TicketEntryDto;
import com.example.BookMyShow.Models.Show;
import com.example.BookMyShow.Models.ShowSeat;
import com.example.BookMyShow.Models.Ticket;
import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TicketRepository;
import com.example.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public String addTicket(TicketEntryDto ticketEntryDto)throws Exception{

        Ticket ticket= TicketConverter.convertDtoToEntity(ticketEntryDto);

        boolean isValidRequest=checkValidityOfRequestedSeats(ticketEntryDto);

        if(isValidRequest==false){
            throw new Exception("requested seats are not available");
        }
        Show show=showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeat>showSeatList=show.getShowSeatList();
        List<String>requestedSeats=ticketEntryDto.getRequestedSeats();
        int totalAmount=0;
        for(ShowSeat showSeat:showSeatList){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                totalAmount=totalAmount+showSeat.getSeatPrice();
                showSeat.setBooked(true);
                showSeat.setBookedAt(new Date());
            }
        }


        ticket.setTotalAmount(totalAmount);
        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setTheaterName(show.getTheater().getName());

        String allottedSeats=getAllottedSeatsFromShowSeats(requestedSeats);
        ticket.setBookedSeats(allottedSeats);

        User user=userRepository.findById(ticketEntryDto.getUserId()).get();
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setTicketId(UUID.randomUUID().toString());

        ticket=ticketRepository.save(ticket);

        List<Ticket>ticketList=show.getListOfTicketsBooked();
        ticketList.add(ticket);
        show.setListOfTicketsBooked(ticketList);

        showRepository.save(show);

        List<Ticket>ticketList1=user.getBookedTickets();
        ticketList1.add(ticket);
        user.setBookedTickets(ticketList1);

        userRepository.save(user);

        String body = "Hi this is to confirm your booking for seat No "+allottedSeats +"for the movie : " + ticket.getMovieName();


        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("bookmyshowclone1@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirming your booked Ticket");

        javaMailSender.send(mimeMessage);


        return "ticket added successfully";


    }
    private String getAllottedSeatsFromShowSeats(List<String>requestedSeats){
        String result="";
        for(String seatNo:requestedSeats){
            result=result+seatNo+", ";
        }
        return result;
    }
    private boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto){

        int showId=ticketEntryDto.getShowId();

        List<String>requestedSeats=ticketEntryDto.getRequestedSeats();

        Show show=showRepository.findById(showId).get();

        List<ShowSeat>showSeatList=show.getShowSeatList();
        for(ShowSeat showSeat:showSeatList){
            String seatNo=showSeat.getSeatNo();
            if(requestedSeats.contains(seatNo)){
                if(showSeat.isBooked()==true){
                    return false;
                }
            }
        }
        return true;
    }
}
