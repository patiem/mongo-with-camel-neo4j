package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.*;
import com.benczykuadama.personmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "userPostService")
public class UserPostServiceImpl implements UserPostService {

    @Autowired
    UserRepository repository;

    @Override
    public void save(String userName, UserPost post) {
        User user = repository.findByName(userName);
        user.publish(post);
        repository.save(user);
    }

    @Override
    public PostWall getPosts(String userName) {
        return getPosts(Arrays.asList(repository.findByName(userName)), PostWallType.USER);
    }

    @Override
    public PostWall getPosts(List<User> users, PostWallType type) {
        PostWall wall = new PostWall();
        wall.setTitle(type);
        users.forEach(user -> user.getPosts().forEach(post -> wall.addPostView(new UserPostView(user.getName(), post))));

        return wall.sortedWall();

    }


}
