package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.model.UserPost;

import java.util.List;
import java.util.Map;

public interface UserPostService {

    void save(String userName, UserPost post);

    List<UserPost> getPosts(String userName);

    Map<User, List<UserPost>> getPosts(List<User> users);
}
