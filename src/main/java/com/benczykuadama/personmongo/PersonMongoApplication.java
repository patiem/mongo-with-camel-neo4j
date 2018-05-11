package com.benczykuadama.personmongo;

import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonMongoApplication implements CommandLineRunner {

    @Autowired
    UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(PersonMongoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

//        "dd-MMM-yyyy"

        repository.deleteAll();

        repository.save(new User("Ala", "Cracow", "01-12-1990"));
        repository.save(new User("Ola", "Cracow", "01-12-1999"));
        repository.save(new User("Ela", "Warsaw", "01-12-1980"));
        repository.save(new User("Tom", "Warsaw", "01-12-2000"));
        repository.save(new User("Tobi", "London", "01-12-1970"));
        repository.save(new User("Tommy", "London", "01-12-2010"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (User person : repository.findAll()) {
            System.out.println(person);
        }
        System.out.println();

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (User per : repository.findAllByNameContaining("la")) {
            System.out.println(per);
        }
    }
}
