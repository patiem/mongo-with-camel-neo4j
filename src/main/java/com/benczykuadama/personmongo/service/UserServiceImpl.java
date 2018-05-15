package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public List<User> findAllByName(String name) {
        return repository.findAllByName(name);
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

    private Date countDate(int years) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, -years);
        Date date = now.getTime();
        System.out.println(date.toString());
        return date;
    }
}
