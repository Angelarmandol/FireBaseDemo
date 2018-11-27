package com.example.atempa.demomvp.domain;

import android.support.annotation.NonNull;

import com.example.atempa.demomvp.domain.model.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonRepositoryImpl implements PersonRepository {
    private FirebaseHelper helper;
    private final static String KEY = "Person";
    private ExecutorService executor;

    public PersonRepositoryImpl() {
        helper = FirebaseHelper.getInstance();
        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void fetchPersons(final GetPersonsCallback callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                helper.getDatabaseReference().child(KEY).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<Person> personList = new ArrayList<>();
                        for (DataSnapshot o : dataSnapshot.getChildren()) {
                            Person person = o.getValue(Person.class);
                            personList.add(person);
                        }
                        callback.onDataLoaded(personList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void savePerson(Person person) {
        helper.getDatabaseReference().child(KEY).child(person.getUid()).setValue(person);
    }
}
