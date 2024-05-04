package com.searchservice.model;

public class SearchCriteria {

    private String artistName;
    private String venueName;
    private String dateRange;
    private int maxPrice;
    private int lowPrice;

    // Getters
    public String getArtistName() {
        return artistName;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getDateRange() {
        return dateRange;
    }

    public int getMaxPrice() {
        return maxPrice;
    }
    
    public int getlowPrice() {
    	return lowPrice;
    }

    // Setters
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }
    
    public void setLowPrice(int lowPrice) {
        this.lowPrice = lowPrice;
    }
}
