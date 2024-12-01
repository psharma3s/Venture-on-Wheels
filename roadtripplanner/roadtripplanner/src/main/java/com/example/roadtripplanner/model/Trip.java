package com.example.roadtripplanner.model;

import java.time.LocalDate;

public class Trip {

    private Integer tripId;
    private Integer userId;
    private String startLocation;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;

    public Trip(Integer tripId, Integer userId, String startLocation, String destination, LocalDate startDate, LocalDate endDate) {
        this.tripId = tripId;
        this.userId = userId;
        this.startLocation = startLocation;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", userId=" + userId +
                ", startLocation='" + startLocation + '\'' +
                ", destination='" + destination + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
