package com.example.mystudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Inscription extends AppCompatActivity {

    private EditText nomInput, prenomInput, emailInput, passwordInput;
    private Button registerButton;
    private EtudiantDAO etudiantDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        // Initialize views
        nomInput = findViewById(R.id.nomInput);
        prenomInput = findViewById(R.id.prenomInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        registerButton = findViewById(R.id.registerButton);

        // Initialize DAO
        etudiantDAO = new EtudiantDAO(this);

        // Set up the register button click listener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values
                String nom = nomInput.getText().toString().trim();
                String prenom = prenomInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                // Validate the input
                if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    // Show error message if input is empty
                    Toast.makeText(Inscription.this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
                } else {
                    // Create a new Etudiant object
                    Etudiant newEtudiant = new Etudiant(nom, prenom, email, password);

                    // Try inserting the Etudiant into the database
                    long id = etudiantDAO.insertEtudiant(newEtudiant);

                    if (id != -1) {
                        // Successfully added the Etudiant, show success message
                        Toast.makeText(Inscription.this, "Inscription réussie! Redirection vers l'authentification...", Toast.LENGTH_LONG).show();
                        // Redirect to the login screen (MainActivity)
                        Intent intent = new Intent(Inscription.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Close the Inscription activity
                    } else {
                        // Error occurred while adding the Etudiant
                        Toast.makeText(Inscription.this, "Une erreur s'est produite lors de l'inscription", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
