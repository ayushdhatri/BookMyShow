package com.bookmyshow.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "shows")
public class Show extends BaseModel {
    @ManyToOne
    private Movie movie;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @ManyToOne
    private Auditorium auditorium;

    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats;

}
