package com.bookmyshow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel{
    private String seatNumber;

    private int rowValue;

    private int colValue;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    @ManyToOne()
    private Auditorium auditorium;
}





