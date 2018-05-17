package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.model.Invitation;
import com.benczykuadama.personmongo.model.User;

import java.util.List;

public interface FriendService {

    void save(User user);

    List<Friend> findAll();

    Friend findByName(String name);

    List<Friend> findAllFriends(String name);

    List<Friend> findNetwork(String name);

    void invite(String user, String friend);

    List<Invitation> showInvitations(String name);

    void makeFriends(String user, String friend);

    void unfriend(String user, String friend);

}
