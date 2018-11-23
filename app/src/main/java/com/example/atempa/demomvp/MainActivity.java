package com.example.atempa.demomvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.atempa.demomvp.domain.FirebaseHelper;
import com.example.atempa.demomvp.domain.model.Person;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    EditText txtName, txtLastName, txtEmail;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txt_name);
        txtLastName = findViewById(R.id.txt_last_name);
        txtEmail = findViewById(R.id.txt_email);
        recyclerView = findViewById(R.id.rv_list_persons);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String name = txtName.getText().toString();
        String lastName = txtLastName.getText().toString();
        String email = txtEmail.getText().toString();
        Person person = null;

        switch (item.getItemId()) {
            case R.id.icon_add: {
                person = new Person();
                person.setUid(UUID.randomUUID().toString());
                person.setName(name);
                person.setLastName(lastName);
                person.setEmail(email);
                FirebaseHelper.getInstance().getDatabaseReference().child("Person").child(person.getUid()).setValue(person);
                showMessage("Agregado");
                clean();
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

    private void clean() {
        txtName.setText("");
        txtLastName.setText("");
        txtEmail.setText("");
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
