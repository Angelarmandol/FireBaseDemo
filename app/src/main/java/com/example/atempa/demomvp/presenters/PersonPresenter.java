package com.example.atempa.demomvp.presenters;

public interface PersonPresenter {
    void getPersonList();
    void createPerson(String name, String lastName, String email);
    void updatePerson(String name, String lastName, String email);
    void deletePerson(String uid);
}
