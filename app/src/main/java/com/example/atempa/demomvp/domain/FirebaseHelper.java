package com.example.atempa.demomvp.domain;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    private static FirebaseHelper INSTANCE = null;
    private DatabaseReference databaseReference = null;

    private FirebaseHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseHelper();
        }

        return INSTANCE;
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
