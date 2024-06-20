package com.jayfox.wigell_padel.services;

import com.jayfox.wigell_padel.BookingVO;
import com.jayfox.wigell_padel.entities.Booking;
import com.jayfox.wigell_padel.exceptions.ResourceNotFoundException;
import com.jayfox.wigell_padel.repositories.BookingRepository;
import com.jayfox.wigell_padel.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingService implements BookingServiceInterface{
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RestTemplate restTemplate;
    Logger logger = Logger.getLogger(BookingService.class);

    private static final String EXCHANGE_RATE_API_URL = "https://v6.exchangerate-api.com/v6/a17ebda95e88033c79959772/latest/SEK";

    @Override
    public Booking createNewBooking(Booking booking, String username) {
        booking.setCustomer(customerRepository.findByUserName(username));
        logger.log(Level.WARN,"New booking created");
        return bookingRepository.save(booking);

    }

    /*@Override
    public List<Booking> getMyBookings(String username) {
        List<Booking> bookingList = bookingRepository.findAll();
        List<Booking> listToReturn = new ArrayList<>();
        for (Booking booking : bookingList){
            if (booking.getCustomer().getUserName().equals(username)){
                listToReturn.add(booking);
            }
        }
        return listToReturn;
    }*/
    @Override
    public List<BookingVO> getMyBookings(String username) {
        List<Booking> bookingList = bookingRepository.findAll();
        List<BookingVO> listToReturn = new ArrayList<>();

        // Fetch the exchange rates
        ResponseEntity<Map> response = restTemplate.getForEntity(EXCHANGE_RATE_API_URL, Map.class);
        Map<String, Object> body = response.getBody();
        Map<String, Double> conversionRates = (Map<String, Double>) body.get("conversion_rates");
        double sekToEurRate = conversionRates.get("EUR");

        for (Booking booking : bookingList) {
            if (booking.getCustomer().getUserName().equals(username)) {
                double convertedPrice = booking.getPriceSek() * sekToEurRate;
                listToReturn.add(new BookingVO(booking, convertedPrice));
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
            bookingToUpdate.setPriceSek(booking.getPriceSek());
            bookingToUpdate.setDate(booking.getDate());
            bookingToUpdate.setStartTime(booking.getStartTime());
            bookingToUpdate.setEndTime(booking.getEndTime());
            bookingToUpdate.setTotalPlayers(booking.getTotalPlayers());
            bookingRepository.save(bookingToUpdate);
            logger.log(Level.WARN,"Booking with id "+booking.getId()+" updated");
            return bookingToUpdate;
        } else throw new ResourceNotFoundException("Booking","id",id);

    }


}
