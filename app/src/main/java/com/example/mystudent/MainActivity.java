package com.example.mystudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button loginButton, signUpButton;
    private EtudiantDAO etudiantDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginBtn);
        signUpButton = findViewById(R.id.signUpBtn);

        etudiantDAO = new EtudiantDAO(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    Etudiant etudiant = etudiantDAO.getEtudiantWithLogin(email, password);
                    if (etudiant != null) {
                        Intent intent = new Intent(MainActivity.this, Accueil.class);
                        intent.putExtra("USER_NAME", etudiant.getNom());  // Pass the last name (nom)
                        intent.putExtra("USER_PRENOM", etudiant.getPrenom());
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Échec de l'authentification", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Inscription.class);
                startActivity(intent);
            }
        });
    }
}
