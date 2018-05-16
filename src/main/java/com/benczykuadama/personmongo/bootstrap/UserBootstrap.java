package com.benczykuadama.personmongo.bootstrap;

import com.benczykuadama.personmongo.model.Friend;
import com.benczykuadama.personmongo.model.Friendship;
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
    private User ala;
    private User ola;
    private User ela;
    private User tom;
    private User tobi;
    private User tommy;
    private List<User> team;

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

        Friend alaF = Friend.from(ala);
        Friend olaF = Friend.from(ola);
        Friend elaF = Friend.from(ela);
        Friend tomF = Friend.from(tom);
        Friend tommyF = Friend.from(tommy);
        Friend tobiF = Friend.from(tobi);

        List<Friend> teamF = Arrays.asList(alaF, olaF, elaF, tomF, tommyF, tobiF);

        neoRepository.save(teamF);

        alaF.addFrienship(new Friendship(alaF, olaF));
        alaF.addFrienship(new Friendship(alaF, elaF));
        neoRepository.save(alaF);

        tomF.addFrienship(new Friendship(tobiF, tomF));
        tomF.addFrienship(new Friendship(tommyF, tomF));
        tomF.addFrienship(new Friendship(olaF, tomF));
        neoRepository.save(tomF);

    }

    private void populateMongo() {

        mongoRepository.deleteAll();

        ala = new User("Ala", "Cracow", "01-12-1990");
        ola = new User("Ola", "Cracow", "01-12-1999");
        ela = new User("Ela", "Warsaw", "01-12-1980");
        tom = new User("Tom", "Warsaw", "01-12-2000");
        tobi = new User("Tobi", "London", "01-12-1970");
        tommy = new User("Tommy", "London", "01-12-2010");

        team = Arrays.asList(ala, ola, ela, tom, tobi, tommy);

        mongoRepository.save(team);

    }
}
