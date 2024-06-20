package com.jayfox.wigell_padel.services;

import com.jayfox.wigell_padel.BookingVO;
import com.jayfox.wigell_padel.entities.Booking;

import java.util.List;

public interface BookingServiceInterface {
Booking createNewBooking(Booking booking, String username);

List<BookingVO> getMyBookings(String username);

Booking updateBooking(long id,Booking booking,String username);
}
