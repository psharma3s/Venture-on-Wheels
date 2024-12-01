package com.example.roadtripplanner;

import com.example.roadtripplanner.dao.TripDao;
import com.example.roadtripplanner.model.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TripDaoTest {

    @Autowired
    private TripDao tripDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        // Clear existing data and seed the database with test data
        tripDao.deleteAllTrips();
        tripDao.createTrip(new Trip(null, 1, "New York", "Los Angeles", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 10)));
    }

    @Test
    public void testCreateTrip() {
        Trip newTrip = new Trip(null, 1, "New York", "Los Angeles",
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 10));
        tripDao.createTrip(newTrip);

        assertThat(newTrip.getTripId()).isNotNull();
        Trip fetchedTrip = tripDao.getTripById(newTrip.getTripId());
        assertThat(fetchedTrip.getStartLocation()).isEqualTo("New York");
    }

    @Test
    public void testUpdateTrip() {
        Trip trip = new Trip(null, 1, "Boston", "Chicago",
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 10));
        tripDao.createTrip(trip);

        trip.setStartLocation("San Francisco");
        tripDao.updateTrip(trip);

        Trip updatedTrip = tripDao.getTripById(trip.getTripId());
        assertThat(updatedTrip.getStartLocation()).isEqualTo("San Francisco");
    }

    @Test
    public void testGetAllTripsByUserId_TripsExist() {
        List<Trip> trips = tripDao.getAllTripsByUserId(1);
        assertThat(trips).isNotEmpty(); // Ensure trips exist for user ID 1
    }

    @Test
    public void testGetAllTripsByUserId_NoTrips() {
        List<Trip> trips = tripDao.getAllTripsByUserId(999); // User ID 999 should have no trips
        assertThat(trips).isEmpty(); // Ensure an empty list is returned
    }

    @Test
    public void testDeleteTrip() {
        Trip trip = new Trip(null, 1, "Seattle", "Miami",
                LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 10));
        tripDao.createTrip(trip);

        tripDao.deleteTrip(trip.getTripId());
        Trip deletedTrip = tripDao.getTripById(trip.getTripId());
        assertThat(deletedTrip).isNull();
    }
}
