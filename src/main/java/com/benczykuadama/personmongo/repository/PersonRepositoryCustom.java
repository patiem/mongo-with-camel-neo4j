package com.benczykuadama.personmongo.repository;

import com.benczykuadama.personmongo.model.Person;

import java.util.List;

public interface PersonRepositoryCustom {

    List<Person> findOlderThan(int age);

    List<Person> findYoungerThan(int age);

    List<Person> findBetweenAge(int ageYoung, int ageOld);
}
