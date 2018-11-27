package com.example.atempa.demomvp.domain;

import com.example.atempa.demomvp.domain.model.Person;

import java.util.List;

public interface PersonRepository {
    interface GetPersonsCallback {
        void onDataLoaded(List<Person> persons);
    }

    void fetchPersons(GetPersonsCallback callback);
    void savePerson(Person person);
}
