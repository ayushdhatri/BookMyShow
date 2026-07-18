package com.bookmyshow.services;

import com.bookmyshow.models.ShowSeat;
import com.bookmyshow.models.ShowSeatStatus;
import com.bookmyshow.models.Ticket;
import com.bookmyshow.repositories.ShowSeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
@RequiredArgsConstructor
public class RedisBookingService implements BookingService{
    private final CacheService cacheService;
    private final ShowSeatRepository showSeatRepository;

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
        return Optional.empty();
    }
}
