package com.example.roadtripplanner;

import com.example.roadtripplanner.dao.UserDao;
import com.example.roadtripplanner.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void cleanDatabase() {
        jdbcTemplate.update("TRUNCATE TABLE users RESTART IDENTITY CASCADE");
    }

    @Test
    public void shouldCreateAndRetrieveUser() {
        User newUser = new User(null, "testuser", "test@example.com", "password123");
        userDao.createUser(newUser);

        User retrievedUser = userDao.getUserById(newUser.getUserId());
        assertNotNull(retrievedUser);
        assertEquals("testuser", retrievedUser.getUsername());
    }

    @Test
    public void shouldThrowExceptionForDuplicateUser() {
        User firstUser = new User(null, "testuser", "test@example.com", "password123");
        userDao.createUser(firstUser);

        User duplicateUser = new User(null, "testuser", "duplicate@example.com", "password123");
        assertThrows(DuplicateKeyException.class, () -> userDao.createUser(duplicateUser));
    }

    @Test
    public void shouldRetrieveAllUsers() {
        userDao.createUser(new User(null, "user1", "user1@example.com", "password123"));
        userDao.createUser(new User(null, "user2", "user2@example.com", "password123"));

        List<User> users = userDao.getAllUsers();
        assertThat(users).hasSize(2);
    }

    @Test
    public void shouldRetrieveUserById() {
        User newUser = new User(null, "testuser", "test@example.com", "password123");
        userDao.createUser(newUser);

        User retrievedUser = userDao.getUserById(newUser.getUserId());
        assertNotNull(retrievedUser);
        assertEquals(newUser.getUsername(), retrievedUser.getUsername());
    }
}

