package com.bookmyshow.repositories;

import com.bookmyshow.models.ShowSeat;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findAllByShowId(long showId);

    List<ShowSeat> findAllByShowIdAndSeatIdIn(long showId, List<Long> seatIds);

    @Modifying
    @Query("UPDATE ShowSeat s SET s.showSeatStatus = 1,s.ticket= :ticketId  WHERE s.id IN :ids")
    void updateShowSeatsBulk(@Param("ids") List<Long> ids, @Param("ticketId") long ticketId);

}
