package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.model.Invitation;
import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.repository.FriendRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service(value = "friendService")
public class FriendServiceImpl implements FriendService {


    @Autowired
    FriendRepository repository;

    @Override
    public void save(User user) {
        repository.save(Friend.from(user));
    }

    @Override
    public List<Friend> findAll() {
        return IteratorUtils.toList(repository.findAll().iterator());
    }

    @Override
    public Friend findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Friend> findAllFriends(String name) {
        return IteratorUtils.toList(repository.findAllFriends(name).iterator());
    }

    @Override
    public List<Friend> findNetwork(String name) {
        return IteratorUtils.toList(repository.findNetwork(name).iterator());
    }

    @Override
    public void invite(String user, String friend) {
        repository.invite(user, friend);
    }

    @Override
    public List<Invitation> showInvitations(String name) {
        return IteratorUtils.toList(repository.getInviations(name).iterator());
    }
}
