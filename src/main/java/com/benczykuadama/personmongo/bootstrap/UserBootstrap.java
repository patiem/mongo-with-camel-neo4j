package com.benczykuadama.personmongo.bootstrap;

import com.benczykuadama.personmongo.model.User;
import com.benczykuadama.personmongo.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository repository;

    public UserBootstrap(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        populate();
    }

    private void populate() {

        repository.deleteAll();

        repository.save(new User("Ala", "Cracow", "01-12-1990"));
        repository.save(new User("Ola", "Cracow", "01-12-1999"));
        repository.save(new User("Ela", "Warsaw", "01-12-1980"));
        repository.save(new User("Tom", "Warsaw", "01-12-2000"));
        repository.save(new User("Tobi", "London", "01-12-1970"));
        repository.save(new User("Tommy", "London", "01-12-2010"));

    }
}
