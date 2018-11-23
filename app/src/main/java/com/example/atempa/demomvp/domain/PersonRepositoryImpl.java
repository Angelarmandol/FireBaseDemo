package com.example.atempa.demomvp.domain;

import com.example.atempa.demomvp.domain.model.Person;

public class PersonRepositoryImpl implements PersonRepository {
    private FirebaseHelper helper;
    private final static String KEY = "Person";

    public PersonRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
    }

    @Override
    public void savePerson(Person person) {
        helper.getDatabaseReference().child(KEY).child(person.getUid()).setValue(person);
    }
}
