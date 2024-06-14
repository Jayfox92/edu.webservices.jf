package com.jayfox.wigell_padel.services;

import com.jayfox.wigell_padel.entities.Booking;
import com.jayfox.wigell_padel.entities.Venue;
import com.jayfox.wigell_padel.repositories.BookingRepository;
import com.jayfox.wigell_padel.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenueService implements VenueServiceInterface{
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private BookingRepository bookingRepository;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    @Override
    public String addVenue(Venue venue) {
        venueRepository.save(venue);
        return "Venue created";
    }

    @Override
    public String removeVenue(long id) {
        if(venueRepository.findById(id).isPresent()){
            venueRepository.deleteById(id);
            return "Venue with id "+id+" deleted";
        }
        return "Failed, couldn't find venue with that id";
    }

    @Override
    public String updateVenue(Venue venue) {
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
            return "Venue with id: "+venue.getId()+" updated";
        }
        return "Failed to find venue with supplied id";
    }
    public List<String> getAvailableHours(Long venueId, LocalDate date) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid venue ID"));

        LocalTime openingTime = venue.getOpeningTime();
        LocalTime closingTime = venue.getClosingTime();

        // Retrieve bookings for the venue on the given date
        List<Booking> bookings = bookingRepository.findBookingsForVenueAndDate(venue, date);

        // Generate all possible booking slots within the opening hours
        List<LocalTime> allSlots = new ArrayList<>();
        LocalTime currentTime = openingTime;
        while (!currentTime.isAfter(closingTime.minusHours(1))) {
            allSlots.add(currentTime);
            currentTime = currentTime.plusHours(1);
        }

        // Filter out slots that are already booked
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

    /*public List<String> getAvailableHours(Long venueId, LocalDateTime date) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid venue ID"));

        LocalDateTime openingTime = date.with(LocalTime.of(11, 0));
        LocalDateTime closingTime = date.with(LocalTime.of(15, 0));

        // Retrieve bookings for the venue on the given date
        List<Booking> bookings = bookingRepository.findOverlappingBookings(
                venue, openingTime, closingTime);

        // Generate all possible booking slots within the opening hours
        List<LocalTime> allSlots = new ArrayList<>();
        LocalTime currentTime = openingTime.toLocalTime();
        while (!currentTime.isAfter(closingTime.toLocalTime().minusHours(1))) {
            allSlots.add(currentTime);
            currentTime = currentTime.plusHours(1);
        }

        // Filter out slots that are already booked
        List<LocalTime> bookedSlots = bookings.stream()
                .map(booking -> booking.getStartDate().toLocalTime())
                .collect(Collectors.toList());

        List<String> availableSlots = allSlots.stream()
                .filter(slot -> !bookedSlots.contains(slot))
                .map(slot -> slot.format(TIME_FORMATTER))
                .collect(Collectors.toList());

        return availableSlots;
    }*/
}
