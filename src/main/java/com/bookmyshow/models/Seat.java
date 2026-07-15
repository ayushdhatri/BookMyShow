package com.bookmyshow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel{
    private String seatNumber;

    private int row;

    private int col;

    private SeatType seatType;

    @ManyToOne()
    private Auditorium auditorium;
}





