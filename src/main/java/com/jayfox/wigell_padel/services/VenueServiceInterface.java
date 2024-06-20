package com.jayfox.wigell_padel.services;

import com.jayfox.wigell_padel.entities.Venue;

public interface VenueServiceInterface {
    Venue addVenue(Venue venue);

    void removeVenue(long id);

    Venue updateVenue(Venue venue);

}
