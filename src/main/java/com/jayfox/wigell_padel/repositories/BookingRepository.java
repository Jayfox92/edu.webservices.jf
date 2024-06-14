package com.jayfox.wigell_padel.repositories;

import com.jayfox.wigell_padel.entities.Booking;
import com.jayfox.wigell_padel.entities.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.venue = :venue AND " +
            "((b.startDate < :endDate AND b.endDate > :startDate))")
    List<Booking> findOverlappingBookings(@Param("venue") Venue venue,
                                          @Param("startDate") LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate);
}


    /*@Query("SELECT b FROM Booking b WHERE b.venue = :venue AND " +
            "((b.startDate <= :endDate AND b.endDate >= :startDate))")
    List<Booking> findOverlappingBookings(@Param("venue") Venue venue,
                                          @Param("startDate") LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate);*/





