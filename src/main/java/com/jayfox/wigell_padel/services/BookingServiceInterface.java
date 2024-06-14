package com.jayfox.wigell_padel.services;

import com.jayfox.wigell_padel.entities.Booking;

import java.util.List;

public interface BookingServiceInterface {


    //Kunderna ska kunna göra ett antal aktiviteter med följande endpoints:
    //• Lista lediga tider GET /api/v5/availability


//• Boka tid POST /api/v5/booking
String createNewBooking(Booking booking);
//• Se tidigare och aktiva bokningar GET /api/v5/mybookings
List<Booking> getMyBookings(String username);
    //• Uppdatera bokning PUT /api/v5/bookings/{id}
String updateBooking(long id,Booking booking);






}
