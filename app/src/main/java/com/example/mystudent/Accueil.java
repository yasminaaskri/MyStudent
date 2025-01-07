package com.example.mystudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Accueil extends AppCompatActivity {

    private Button listStudentsBtn;
    private TextView loggedInUserNom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);


        listStudentsBtn = findViewById(R.id.listStudentsButton);
        loggedInUserNom = findViewById(R.id.loggedInUserNom);

        // Retrieve the logged-in user's info from the Intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");
        String userPrenom = intent.getStringExtra("USER_PRENOM");


        if (userName != null && userPrenom != null) {
            loggedInUserNom.setText("Bonjour, " + userPrenom + " " + userName);
        } else {
            loggedInUserNom.setText("No user logged in");
        }



        if (userName != null) {
            loggedInUserNom.setText("Bonjour, " + userName);
        } else {
            loggedInUserNom.setText("No user logged in");
        }
        listStudentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the DisplayEtudiants activity
                Intent intent = new Intent(Accueil.this, DisplayEtudiants.class);
                startActivity(intent);
            }
        });
    }
}
