package com.benczykuadama.personmongo.repository;

import com.benczykuadama.personmongo.model.Person;

import java.util.List;

public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @Override
    public List<Person> findOlderThan(int age) {
        return null;
    }

    @Override
    public List<Person> findYoungerThan(int age) {
        return null;
    }

    @Override
    public List<Person> findBetweenAge(int ageYoung, int ageOld) {
        return null;
    }
}
