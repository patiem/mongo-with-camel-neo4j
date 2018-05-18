package com.benczykuadama.personmongo.service;

import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.model.UserPost;
import com.benczykuadama.personmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Map<User, List<UserPost>> getPosts(List<User> users) {
        return null;
    }


}
