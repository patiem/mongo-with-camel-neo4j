package com.benczykuadama.personmongo.repository;

import com.benczykuadama.personmongo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, Long> {

    List<Person> findAllByName(String name);

    List<Person> findAllByCity(String city);

    List<Person> findAllByNameContaining(String partName);


}
