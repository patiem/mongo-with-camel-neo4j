package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.*;

import java.util.List;

public interface UserPostService {

    void save(String userName, UserPost post);

    PostWall getPosts(String userName);

    PostWall getPosts(List<User> users, PostWallType type);
}
