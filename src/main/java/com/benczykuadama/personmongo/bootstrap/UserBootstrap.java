package com.benczykuadama.personmongo.bootstrap;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.repository.FriendRepository;
import com.benczykuadama.personmongo.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class UserBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository mongoRepository;
    private final FriendRepository neoRepository;

    public UserBootstrap(UserRepository repository, FriendRepository neoRepository) {
        this.mongoRepository = repository;
        this.neoRepository = neoRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        populateMongo();
        populateNeo();

    }

    private void populateNeo() {


        neoRepository.deleteAll();

        Friend bob = new Friend("1", "Bob");
        Friend greg = new Friend("2", "Greg");
        Friend john = new Friend("3", "John");
        Friend eve = new Friend("4", "Eve");
        Friend lucy = new Friend("5", "Lucy");

        List<Friend> team = Arrays.asList(bob, greg, john, lucy, eve);
        neoRepository.save(team);



    }

    private void populateMongo() {

        mongoRepository.deleteAll();

        mongoRepository.save(new User("Ala", "Cracow", "01-12-1990"));
        mongoRepository.save(new User("Ola", "Cracow", "01-12-1999"));
        mongoRepository.save(new User("Ela", "Warsaw", "01-12-1980"));
        mongoRepository.save(new User("Tom", "Warsaw", "01-12-2000"));
        mongoRepository.save(new User("Tobi", "London", "01-12-1970"));
        mongoRepository.save(new User("Tommy", "London", "01-12-2010"));

    }
}
