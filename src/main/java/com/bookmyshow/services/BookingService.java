package com.bookmyshow.services;


import com.bookmyshow.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingService {
    // here we earlier use to put long seatId, which will eventually has lots of issues, such as deadlock and bad user experience
    // instead of using single id and calling this function n times, we should do batch processing, like we should pass
    // list of seats
    boolean blockSeats(long showId, List<Long> seatIds, long userId);

    Optional<Ticket> bookTicket(long showId, List<Long>seatIds, long userId);
}
