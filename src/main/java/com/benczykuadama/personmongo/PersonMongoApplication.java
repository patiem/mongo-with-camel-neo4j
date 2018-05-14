package com.benczykuadama.personmongo;

import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonMongoApplication {

    @Autowired
    UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(PersonMongoApplication.class, args);
    }
}
