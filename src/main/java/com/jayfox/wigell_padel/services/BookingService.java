package com.jayfox.wigell_padel.services;

import com.jayfox.wigell_padel.entities.Booking;
import com.jayfox.wigell_padel.repositories.BookingRepository;
import com.jayfox.wigell_padel.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements BookingServiceInterface{
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Booking createNewBooking(Booking booking, String username) {
        booking.setCustomer(customerRepository.findByUserName(username));
        return bookingRepository.save(booking);

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
    public Booking updateBooking(long id,Booking booking,String username) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if(existingBooking.isPresent()){
            Booking bookingToUpdate = existingBooking.get();
            if(!booking.getCustomer().getUserName().equals(username)){
                return null;
            }
            if(booking.getCustomer()!=null){
                bookingToUpdate.setCustomer(booking.getCustomer());
            }
            if(booking.getVenue()!=null){
                bookingToUpdate.setVenue(booking.getVenue());
            }
            bookingToUpdate.setPrice(booking.getPrice());
            bookingToUpdate.setDate(booking.getDate());
            bookingToUpdate.setStartTime(booking.getStartTime());
            bookingToUpdate.setEndTime(booking.getEndTime());
            bookingToUpdate.setTotalPlayers(booking.getTotalPlayers());
            bookingRepository.save(bookingToUpdate);
            return bookingToUpdate;
        }
        return null;
    }
}
