package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "friendService")
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendRepository repository;

    @Override
    public void save(Friend friend) {
        repository.save(friend);
    }

    @Override
    public Iterable<Friend> findAll() {
        return repository.findAll();

    }

    @Override
    public Friend findByName(String name) {
        return repository.findByName(name);
    }
}
