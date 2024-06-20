package com.jayfox.wigell_padel.controllers;

import com.jayfox.wigell_padel.entities.Venue;
import com.jayfox.wigell_padel.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v5")
public class VenueController {
    @Autowired
    private VenueService venueService;


    @PostMapping("/addfield")
    public Venue addVenue(@RequestBody Venue venue){

        return venueService.addVenue(venue);
    }


    @DeleteMapping("/deletefield/{id}")
    public ResponseEntity<String> deleteVenue(@PathVariable("id")long id){
         venueService.removeVenue(id);
         return ResponseEntity.ok("Venue deleted");
    }

    @PutMapping("/updateinfo")
    public Venue updateVenue(@RequestBody Venue venue){
        return venueService.updateVenue(venue);
    }


    @GetMapping("/availability")
    public List<String> getVenueAvailability(@RequestBody Venue venue, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        return venueService.getAvailableHours(venue.getId(), localDate);
    }
}
