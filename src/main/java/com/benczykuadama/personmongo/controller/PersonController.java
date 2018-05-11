package com.benczykuadama.personmongo.controller;

import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PersonController {

    @Autowired
    UserService service;

    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestBody User user) {
        return service.save(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findBy/containing")
    public List<User> getUserByNameContaining(@Param("name") String name) {
        return service.findAllByNameContaining(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findBy/dateBetween")
    public List<User> getUserByNameContaining(@Param("start") int start, @Param("end") int end) {
        return service.findAllByBirthDateBetween(start, end);
    }

}
