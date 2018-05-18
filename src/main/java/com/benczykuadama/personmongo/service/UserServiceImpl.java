package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User save(User user) {
        return repository.insert(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<User> findAllByCity(String city) {
        return repository.findAllByCity(city);
    }

    @Override
    public List<User> findAllByNameContaining(String partName) {
        return repository.findAllByNameContaining(partName);
    }

    @Override
    public List<User> findAllByBirthDateBetween(int startDate, int endDate) {
        return repository.findAllByBirthDateBetween(countDate(startDate), countDate(endDate));
    }

    @Override
    public List<User> findAllByNameAndCity(String name, String city) {
        return repository.findAllByNameAndCity(name, city);
    }

    @Override
    public List<User> findAllByBirthDateBetweenAndName(int startDate, int endDate, String name) {
        return repository.findAllByBirthDateBetweenAndName(countDate(startDate), countDate(endDate), name);
    }

    @Override
    public List<User> getUsers(List<Friend> friends) {
        List<User> users = friends.stream().map(friend -> findByName(friend.getName())).collect(Collectors.toList());
        return users;
    }

    private Date countDate(int years) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, -years);
        Date date = now.getTime();
        System.out.println(date.toString());
        return date;
    }
}
