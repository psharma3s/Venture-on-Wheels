package com.example.roadtripplanner.dao;

import com.example.roadtripplanner.model.Trip;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface TripDao {

    List<Trip> getAllTripsByUserId(Integer userId);

    Trip getTripById(Integer tripId);
    void createTrip(Trip trip);
    void updateTrip(Trip trip);
    void deleteTrip(Integer tripId);

    void deleteAllTrips();
}
