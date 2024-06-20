package com.jayfox.wigell_padel;

import com.jayfox.wigell_padel.entities.Booking;

public class BookingVO {
        private Booking booking;
        private double priceEur;

        public BookingVO(Booking booking, double priceEur) {
            this.booking = booking;
            this.priceEur = priceEur;
        }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public double getPriceEur() {
        return priceEur;
    }

    public void setPriceEur(double priceEur) {
        this.priceEur = priceEur;
    }
}
