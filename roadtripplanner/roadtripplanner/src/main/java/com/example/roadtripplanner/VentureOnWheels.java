package com.example.roadtripplanner;

import com.example.roadtripplanner.dao.TripDao;
import com.example.roadtripplanner.dao.UserDao;
import com.example.roadtripplanner.model.Trip;
import com.example.roadtripplanner.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VentureOnWheels {

	public static void main(String[] args) {
		SpringApplication.run(VentureOnWheels.class, args);
	}
	


}

