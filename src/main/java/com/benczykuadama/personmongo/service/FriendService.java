package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.Friend;

import java.util.List;

public interface FriendService {

    void save(Friend friend);

    Iterable<Friend> findAll();

    Friend findByName(String name);
}
