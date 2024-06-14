package com.jayfox.wigell_padel.controllers;

import com.jayfox.wigell_padel.entities.Venue;
import com.jayfox.wigell_padel.services.BookingService;
import com.jayfox.wigell_padel.services.VenueService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v5")
public class VenueController {
    @Autowired
    private VenueService venueService;


    @PostMapping("/addfield")
    public String addVenue(@RequestBody Venue venue){

        return venueService.addVenue(venue);
    }
    //    Lägga till bana POST /api/v5/addfield

    @DeleteMapping("/deletefield/{id}")
    public String deleteVenue(@PathVariable("id")long id){
        return venueService.removeVenue(id);
    }
//• Ta bort en bana DELETE /api/v5/deletefield/{id}
    @PutMapping("/updateinfo")
    public String updateVenue(@RequestBody Venue venue){
        return venueService.updateVenue(venue);
    }
//• Uppdatera information för bana PUT /api/v5/updateinfo

    @GetMapping("/availability")
    public List<String> getVenueAvailability(@RequestBody Venue venue, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return venueService.getAvailableHours(venue.getId(), localDate);
    }
}
