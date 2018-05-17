package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.model.User;

import java.util.Collection;
import java.util.List;

public interface FriendService {

    void save(User user);

    Collection<Friend> findAll();

    Friend findByName(String name);

    List<Friend> findAllFriends(String name);

    void invite(String user, String friend);
}
