package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.PostWall;
import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.model.UserPost;
import com.benczykuadama.personmongo.model.UserPostView;
import com.benczykuadama.personmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public List<UserPost> getPosts(String userName) {
        return repository.findByName(userName).getPosts();
    }

    @Override
    public List<UserPostView> getPosts(List<User> users) {
        PostWall wall = new PostWall();
        users.forEach(user -> user.getPosts().forEach(post -> wall.addPostView(new UserPostView(user.getName(), post))));

        return wall.sortPostsByDate();

    }


}
