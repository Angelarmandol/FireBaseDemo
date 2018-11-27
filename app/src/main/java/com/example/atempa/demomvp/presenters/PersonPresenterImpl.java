package com.example.atempa.demomvp.presenters;

import com.example.atempa.demomvp.PersonView;
import com.example.atempa.demomvp.domain.PersonRepository;
import com.example.atempa.demomvp.domain.model.Person;

import java.util.List;
import java.util.UUID;

public class PersonPresenterImpl implements PersonPresenter {
    private PersonView mView;
    private PersonRepository mRepository;

    public PersonPresenterImpl(PersonView view, PersonRepository repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void getPersonList() {
        mView.showProgressBar();
        mRepository.fetchPersons(
          new PersonRepository.GetPersonsCallback() {
            @Override
            public void onDataLoaded(List<Person> persons) {
              mView.fillAdapter(persons);
              mView.hideProgressBar();
            }
          }
        );
    }

    @Override
    public void createPerson(String name, String lastName, String email) {
        mView.showProgressBar();
        if ((name != null && !name.equals("")) && (lastName != null && !lastName.equals("")) && (email != null && !email.equals(""))) {
            Person person = new Person(
               UUID.randomUUID().toString(),
               name,
               lastName,
               email
            );

            mRepository.savePerson(person);
            mView.hideProgressBar();
            mView.showMessage("Registrado");
        } else {
            mView.hideProgressBar();
            mView.showMessage("Ingresa los datos solicitados");
        }

        mView.clean();
    }

    @Override
    public void updatePerson(String name, String lastName, String email) {

    }

    @Override
    public void deletePerson(String uid) {

    }
}
