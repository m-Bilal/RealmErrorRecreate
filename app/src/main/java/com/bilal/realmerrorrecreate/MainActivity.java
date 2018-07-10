package com.bilal.realmerrorrecreate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    Button saveButton;
    Button readButton;
    List<MyRealmModel> myRealmModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeRealm();
        saveButton = findViewById(R.id.button_save);
        readButton = findViewById(R.id.button_read);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyRealmModel.saveModels();
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRealmModels = MyRealmModel.getModels();
                for (MyRealmModel i : myRealmModels) {
                    // Incorrect values in these logs
                    Log.d(TAG, "id: " + i.id);
                    Log.d(TAG, "minAge: " + i.minAge);
                    Log.d(TAG, "maxAge: " + i.maxAge);
                    Log.d(TAG, "name: " + i.name);
                }
            }
        });
    }

    private void initializeRealm() {
        // initialize Realm
        Realm.init(getApplicationContext());

        // create your Realm configuration
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .name("myRealm")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
