package com.jayfox.wigell_padel.services;

import com.jayfox.wigell_padel.entities.Booking;
import com.jayfox.wigell_padel.entities.Venue;
import com.jayfox.wigell_padel.exceptions.ResourceNotFoundException;
import com.jayfox.wigell_padel.repositories.BookingRepository;
import com.jayfox.wigell_padel.repositories.VenueRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VenueService implements VenueServiceInterface{
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private BookingRepository bookingRepository;


    Logger logger = Logger.getLogger(VenueService.class);


    @Override
    public Venue addVenue(Venue venue) {
        venueRepository.save(venue);
        logger.log(Level.WARN,"Venue created");
        return venue;

    }

    @Override
    public void removeVenue(long id) {
        if(venueRepository.findById(id).isPresent()){
            venueRepository.deleteById(id);
            logger.log(Level.WARN,"Venue with id "+id+" deleted");

        } else throw new ResourceNotFoundException("venue","id", id);

    }

    @Override
    public Venue updateVenue(Venue venue) {
        if(venueRepository.findById(venue.getId()).isPresent()){
            Venue venueToUpdate = venueRepository.findById(venue.getId()).get();
            if(venue.getBooking()!=null){
                venueToUpdate.setBooking(venue.getBooking());
            }
            venueToUpdate.setName(venue.getName());
            venueToUpdate.setLocation(venue.getLocation());
            venueToUpdate.setFieldType(venue.getFieldType());
            venueToUpdate.setOpeningTime(venue.getOpeningTime());
            venueToUpdate.setClosingTime(venue.getClosingTime());
            venueRepository.save(venueToUpdate);
            logger.log(Level.WARN,"Venue with id "+venue.getId()+" updated");
            return venueToUpdate;
        } else throw new ResourceNotFoundException("venue","id",venue.getId());

    }
    public List<String> getAvailableHours(Long venueId, LocalDate date) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new ResourceNotFoundException("venue","id",venueId));

        LocalTime openingTime = venue.getOpeningTime();
        LocalTime closingTime = venue.getClosingTime();

        List<Booking> bookings = bookingRepository.findBookingsForVenueAndDate(venue, date);


        List<LocalTime> allSlots = new ArrayList<>();
        LocalTime currentTime = openingTime;
        while (!currentTime.isAfter(closingTime.minusHours(1))) {
            allSlots.add(currentTime);
            currentTime = currentTime.plusHours(1);
        }


        for (Booking booking : bookings) {
            LocalTime bookedSlot = booking.getStartTime();
            while (!bookedSlot.isAfter(booking.getEndTime().minusHours(1))) {
                allSlots.remove(bookedSlot);
                bookedSlot = bookedSlot.plusHours(1);
            }
        }

        List<String> availableSlots = allSlots.stream()
                .map(LocalTime::toString)
                .collect(Collectors.toList());

        return availableSlots;
    }

}
