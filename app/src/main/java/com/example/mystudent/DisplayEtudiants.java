package com.example.mystudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DisplayEtudiants extends AppCompatActivity {

    private ListView etudiantListView;
    private EtudiantListAdapter etudiantListAdapter;
    private ArrayList<Etudiant> etudiantList;
    private EtudiantDAO etudiantDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_etudiants);

        // Initialize the ListView and student list
        etudiantListView = findViewById(R.id.etudiantListView);
        etudiantList = new ArrayList<>();

        // Initialize the DAO to fetch students from the database
        etudiantDAO = new EtudiantDAO(this);

        // Fetch all students from the database
        etudiantList = (ArrayList<Etudiant>) etudiantDAO.getAll();

        // Create the adapter and set it to the ListView
        etudiantListAdapter = new EtudiantListAdapter(this, etudiantList);
        etudiantListView.setAdapter(etudiantListAdapter);
    }
}
