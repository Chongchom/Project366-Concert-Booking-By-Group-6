package com.searchservice.model;

import java.time.LocalDate;

public class Concert {

    private String artistName;
    private String venueName;
    private LocalDate date;

    // Getters
    public String getArtistName() {
        return artistName;
    }

    public String getVenueName() {
        return venueName;
    }

    public LocalDate getDate() {
        return date;
    }

    // Setters
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
