package com.jayfox.wigell_padel.repositories;

import com.jayfox.wigell_padel.entities.Booking;
import com.jayfox.wigell_padel.entities.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByVenueAndDate(Venue venue, LocalDate date);
    @Query("SELECT b FROM Booking b WHERE b.venue = :venue AND b.date = :date")
    List<Booking> findBookingsForVenueAndDate(@Param("venue") Venue venue, @Param("date") LocalDate date);

}








