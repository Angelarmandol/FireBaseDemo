package com.example.atempa.demomvp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.atempa.demomvp.domain.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private ArrayList<Person> dataset;

    public PersonAdapter() {
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Person person = dataset.get(i);
        viewHolder.tvName.setText(person.getName());
        viewHolder.tvEmail.setText(person.getEmail());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addPersons(List<Person> personList) {
        Log.d("ENTRARON", "Tam " + personList.size());
        dataset.addAll(personList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_person_name);
            tvEmail = itemView.findViewById(R.id.tv_person_email);
        }
    }
}
