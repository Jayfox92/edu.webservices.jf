package com.jayfox.wigell_padel.services;

import com.jayfox.wigell_padel.entities.Venue;

public interface VenueServiceInterface {

    //• Lista lediga tider GET /api/v5/availability

    //Administratörer ska kunna göra ett antal aktiviteter med följande endpoints:
    Venue addVenue(Venue venue);
    //• Lägga till bana POST /api/v5/addfield
    void removeVenue(long id);
//• Ta bort en bana DELETE /api/v5/deletefield/{id}
    Venue updateVenue(Venue venue);
//• Uppdatera information för bana PUT /api/v5/updateinfo
}
