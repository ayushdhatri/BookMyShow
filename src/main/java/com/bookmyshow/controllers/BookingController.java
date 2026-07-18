package com.bookmyshow.controllers;

import com.bookmyshow.dto.BlockSeatsRequestDto;
import com.bookmyshow.dto.BookSeatRequestDto;
import com.bookmyshow.models.Ticket;
import com.bookmyshow.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public boolean blockSeats(@RequestBody BlockSeatsRequestDto blockSeatsRequestDto){
        System.out.println("show id is: " + blockSeatsRequestDto.getShowId());
        System.out.println("List of seat ids : " + blockSeatsRequestDto.getSeatIds());
        System.out.println("User id is : " + blockSeatsRequestDto.getUserId());
        return bookingService.blockSeats(blockSeatsRequestDto.getShowId(), blockSeatsRequestDto.getSeatIds(), blockSeatsRequestDto.getUserId());
    }

    @PostMapping("/confirm")
    public Optional<Ticket> confirmBooking(@RequestBody BookSeatRequestDto bookSeatRequestDto){
        return bookingService.bookTicket(bookSeatRequestDto.getShowId(), bookSeatRequestDto.getSeatIds(), bookSeatRequestDto.getUserId());
    }


}
