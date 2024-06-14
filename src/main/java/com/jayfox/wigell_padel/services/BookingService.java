package com.jayfox.wigell_padel.services;

import com.jayfox.wigell_padel.entities.Booking;
import com.jayfox.wigell_padel.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements BookingServiceInterface{
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public String createNewBooking(Booking booking) {
        bookingRepository.save(booking);
        return "New booking created";
    }

    @Override
    public List<Booking> getMyBookings(String username) {
        List<Booking> bookingList = bookingRepository.findAll();
        List<Booking> listToReturn = new ArrayList<>();
        for (Booking booking : bookingList){
            if (booking.getCustomer().getUserName().equals(username)){
                listToReturn.add(booking);
            }
        }
        return listToReturn;
    }

    @Override
    public String updateBooking(long id,Booking booking) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if(existingBooking.isPresent()){
            Booking bookingToUpdate = existingBooking.get();
            if(booking.getCustomer()!=null){
                bookingToUpdate.setCustomer(booking.getCustomer());
            }
            if(booking.getVenue()!=null){
                bookingToUpdate.setVenue(booking.getVenue());
            }
            bookingToUpdate.setPrice(booking.getPrice());
            bookingToUpdate.setStartDate(booking.getStartDate());
            bookingToUpdate.setEndDate(booking.getEndDate());
            bookingToUpdate.setTotalPlayers(booking.getTotalPlayers());
            bookingRepository.save(bookingToUpdate);
            return "Booking updated";
        }
        return "Failed, couldn't find booking with supplied id";
    }
}
