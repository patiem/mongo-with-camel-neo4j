package com.benczykuadama.personmongo.repository;

import com.benczykuadama.personmongo.TestConf;
import com.benczykuadama.personmongo.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConf.class)
@SpringBootTest(classes = TestConf.class)
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

        User userFromDB = repository.findByName(user.getName());
        assertEquals(user, userFromDB);
    }
}