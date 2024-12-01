package com.example.roadtripplanner.dao;

import com.example.roadtripplanner.model.Trip;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class JdbcTripDao implements TripDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTripDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Trip> getAllTripsByUserId(Integer userId) {

        String sql = "SELECT * FROM trips WHERE user_id = ?";

        return jdbcTemplate.query(sql,(rs, rowNum) -> new Trip (
                rs.getInt("trip_id"),
                rs.getInt("user_id"),
                rs.getString("start_location"),
                rs.getString("destination"),
                rs.getDate("start_date").toLocalDate(),
                rs.getDate("end_date").toLocalDate()
        ), userId);
    }

    @Override
    public Trip getTripById(Integer tripId) {

        String sql = "SELECT * FROM trips WHERE trip_id = ?";

        List<Trip> trips = jdbcTemplate.query(sql, (rs, rowNum) -> new Trip (

                rs.getInt("trip_id"),
                rs.getInt("user_id"),
                rs.getString("start_location"),
                rs.getString("destination"),
                rs.getDate("start_date").toLocalDate(),
                rs.getDate("end_date").toLocalDate()
        ), tripId);

        return trips.isEmpty() ? null : trips.get(0);
    }

    @Override
    public void createTrip(Trip trip) {
        String sql = "INSERT INTO trips (user_id, start_location, destination, start_date, end_date) " +
                "VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"trip_id"}); // Specify the key column
            ps.setInt(1, trip.getUserId());
            ps.setString(2, trip.getStartLocation());
            ps.setString(3, trip.getDestination());
            ps.setObject(4, trip.getStartDate());
            ps.setObject(5, trip.getEndDate());
            return ps;
        }, keyHolder);

        // Retrieve and set the generated trip_id
        if (keyHolder.getKey() != null) {
            trip.setTripId(keyHolder.getKey().intValue());
        }
    }

    @Override
    public void updateTrip(Trip trip) {
        String sql = "UPDATE trips SET start_location = ?, destination = ?, start_date = ?, end_date = ? WHERE trip_id = ?";
        jdbcTemplate.update(sql, trip.getStartLocation(), trip.getDestination(),
                trip.getStartDate(), trip.getEndDate(), trip.getTripId());
    }

    @Override
    public void deleteTrip(Integer tripId) {
        String sql = "DELETE FROM trips WHERE trip_id = ?";
        jdbcTemplate.update(sql, tripId);
    }

    @Override
    public void deleteAllTrips() {
        String sql = "DELETE FROM trips";
    }

}
