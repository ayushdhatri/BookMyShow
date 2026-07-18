package com.bookmyshow.services;

import com.bookmyshow.models.*;
import com.bookmyshow.repositories.ShowRepository;
import com.bookmyshow.repositories.ShowSeatRepository;
import com.bookmyshow.repositories.TicketRepository;
import com.bookmyshow.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
@RequiredArgsConstructor
public class RedisBookingService implements BookingService{
    private final CacheService cacheService;
    private final ShowSeatRepository showSeatRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;

    @Override
    public boolean blockSeats(long showId, List<Long> seatIds, long userId) {
        System.out.println("Printing cache before logic");
        cacheService.getAllKeysAndValues();
        // 1. We will first of all check if the seats are available or not
        // 1.a Check if the seats are not booked already
        seatIds.forEach(System.out::println);
        List<ShowSeat> showSeats = showSeatRepository.findAllByShowIdAndSeatIdIn(showId, seatIds);
        System.out.println("size of showSeats is " + showSeats.size());
        for(ShowSeat seat : showSeats){
            System.out.println("Seat with Id " + seat.getId() + " with its status as " + seat.getShowSeatStatus());
            if(seat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED)){
                return false;
            }
        }
        // 1.b check if the seats are not blocked already
        for(ShowSeat seat : showSeats){
            String status = (String)cacheService.get("seatId-"+seat.getId()+"-userId-"+userId);
            if(status != null){
                return false;
            }
        }
        // 2. If all the seats are available then we will block the eat in redis -seatId - userId
        for(ShowSeat seat : showSeats){
            cacheService.set("seatId-"+seat.getId()+"-userId-"+userId,"LOCKED");

        }

        System.out.println("Printing cache after logic");
        cacheService.getAllKeysAndValues();
        return true;
    }

    @Override
    public Optional<Ticket> bookTicket(long showId, List<Long> seatIds, long userId) {
        // if we come to this stage then
        // 1. In redis check if the user has lock for all the seats that they are trying to book
        for(Long seatId : seatIds){
            String status = (String) cacheService.get("seatId-"+seatId+"-userId-"+userId);
            if(status == null){
                return Optional.empty();
            }
        }
        // Create a new ticket
        User user = userRepository.findById(userId).get();
        Show show = showRepository.findById(showId).get();
        Ticket ticket = createTicketAndBookSeat(show, user, seatIds);

        return Optional.of(ticket);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    protected Ticket createTicketAndBookSeat(Show show, User user, List<Long> seatIds){
        Ticket ticket = new Ticket();
        ticket.setAmount(100);
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setStatus(TicketStatus.BOOKED);
        ticket = ticketRepository.save(ticket);

        showSeatRepository.updateShowSeatsBulk(seatIds, ticket.getId());
        return ticket;

    }
}
