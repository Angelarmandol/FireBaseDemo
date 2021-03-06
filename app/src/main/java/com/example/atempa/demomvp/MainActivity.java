package com.example.atempa.demomvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.atempa.demomvp.domain.PersonRepositoryImpl;
import com.example.atempa.demomvp.domain.model.Person;
import com.example.atempa.demomvp.presenters.PersonPresenter;
import com.example.atempa.demomvp.presenters.PersonPresenterImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PersonView {
    EditText txtName, txtLastName, txtEmail;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    PersonAdapter adapter;
    PersonPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txt_name);
        txtLastName = findViewById(R.id.txt_last_name);
        txtEmail = findViewById(R.id.txt_email);
        progressBar = findViewById(R.id.progressBar);
        // Create List with RecyclerView
        recyclerView = findViewById(R.id.rv_list_persons);
        adapter = new PersonAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Create a Presenter instance
        presenter = new PersonPresenterImpl(this, new PersonRepositoryImpl());
        presenter.getPersonList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_add: {
                presenter.createPerson(
                   txtName.getText().toString(),
                   txtLastName.getText().toString(),
                   txtEmail.getText().toString()
                );
                break;
            }
            case R.id.icon_save: {
                showMessage("Actualizado");
                break;
            }
            case R.id.icon_delete: {
                showMessage("Eliminado");
                break;
            }
            default:break;
        }

        return true;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void clean() {
        txtName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillAdapter(List<Person> personList) {
        adapter.addPersons(personList);
    }
}
