package com.example.atempa.demomvp;

import com.example.atempa.demomvp.domain.model.Person;

import java.util.List;

public interface PersonView {
    void showMessage(String message);
    void clean();
    void showProgressBar();
    void hideProgressBar();
    void fillAdapter(List<Person> personList);
}
