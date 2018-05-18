package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface UserService {

    User save(User user);

    List<User> findAll();

    User findByName(String name);

    List<User> findAllByCity(String city);

    List<User> findAllByNameContaining(String partName);

    List<User> findAllByBirthDateBetween(int startDate, int endDate);

    List<User> findAllByNameAndCity(String name, String city);

    List<User> findAllByBirthDateBetweenAndName(int startDate, int endDate, String name);

    List<User> getUsers(List<Friend> friends);

}
