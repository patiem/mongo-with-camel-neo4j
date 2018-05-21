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
import java.util.Optional;


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
        repository.invite(user, friend, new Date());
    }

    @Override
    public void makeFriends(String user, String friend) {
        repository.makeFriend(user, friend, new Date());
    }

    @Override
    public void unfriend(String user, String friend) {
        repository.unfriend(user, friend);
    }

    @Override
    public Integer distanceBetween(String user, String friend) {
        for (int i = 0; i < 3; i++) {
            System.out.println(user + " : " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer distance = Optional.ofNullable(repository.distanceBetween(user, friend)).orElse(0);
        return distance;
    }

    @Override
    public List<Friend> pathTo(String user, String friend) {
        return repository.pathTo(user, friend);
    }

    @Override
    public List<Invitation> showInvitations(String name) {
        return IteratorUtils.toList(repository.getInvitations(name).iterator());
    }


}
