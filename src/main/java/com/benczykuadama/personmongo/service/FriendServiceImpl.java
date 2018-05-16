package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.repository.FriendRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;


@Service(value = "friendService")
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendRepository repository;

    @Override
    public void save(User user) {
        repository.save(Friend.from(user));
    }

    @Override
    public Collection<Friend> findAll() {
        return IteratorUtils.toList(repository.findAll().iterator());

    }

    @Override
    public Friend findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void invite(String user, String friend) {
        Date date = new Date();
        repository.invite(user, friend, date);
    }
}
