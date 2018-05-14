package com.benczykuadama.personmongo.repository;

import com.benczykuadama.personmongo.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
@ActiveProfiles("test")
public class UserRepositoryIT {

    @Autowired
    UserRepository repository;

    @Before
    public void setup() {
        repository.deleteAll();
    }

    @Test
    public void userIsAddedToDB() {
        User user = new User("Dorota", "Zakopane", "01-12-1990");
        repository.save(user);
        assertNotNull(user.getId());

        User userFromDB = repository.findAllByName(user.getName()).get(0);
        assertEquals(user, userFromDB);
    }
}