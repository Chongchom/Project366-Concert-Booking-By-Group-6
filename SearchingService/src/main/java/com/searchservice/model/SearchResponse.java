package com.searchservice.model;

import java.util.List;

public class SearchResponse {

    private List<Concert> concerts;
    private int totalResults;
    private String errorMessage;

    // Getters
    public List<Concert> getConcerts() {
        return concerts;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // Setters
    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
