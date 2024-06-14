package com.jayfox.wigell_padel.controllers;

import com.jayfox.wigell_padel.entities.Booking;
import com.jayfox.wigell_padel.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v5")
public class BookingController {
    @Autowired
    private BookingService bookingService;


    @PostMapping("/booking")
    public Booking createBooking(@RequestBody Booking booking){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return bookingService.createNewBooking(booking, username);
    }
//• Boka tid POST /api/v5/booking
//• Se tidigare och aktiva bokningar GET /api/v5/mybookings
    @GetMapping("/mybookings")
    public List<Booking> myBookings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return bookingService.getMyBookings(username);
    }
    @PutMapping("/bookings/{id}")
    public Booking updateBooking(@PathVariable("id") long id,@RequestBody Booking booking){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return bookingService.updateBooking(id,booking,username);

    }
//• Uppdatera bokning PUT /api/v5/bookings/{id}
}
