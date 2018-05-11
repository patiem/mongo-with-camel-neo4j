package com.benczykuadama.personmongo.repository;

import com.benczykuadama.personmongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    List<User> findAllByName(String name);

    List<User> findAllByCity(String city);

    List<User> findAllByNameContaining(String partName);

    List<User> findAllByBirthDateBetween(Date startDate, Date endDate);

    List<User> findAllByNameAndCity(String name, String city);

    List<User> findAllByBirthDateBetweenAndName(Date startDate, Date endDate, String name);

}
