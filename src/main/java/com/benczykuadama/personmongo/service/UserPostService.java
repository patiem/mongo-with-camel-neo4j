package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.model.UserPost;
import com.benczykuadama.personmongo.model.UserPostView;

import java.util.List;

public interface UserPostService {

    void save(String userName, UserPost post);

    List<UserPost> getPosts(String userName);

    List<UserPostView> getPosts(List<User> users);
}
