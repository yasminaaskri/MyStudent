package com.example.mystudent;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO {

    private DataBaseHandler dbHelper;

    public EtudiantDAO(Context context) {
        dbHelper = new DataBaseHandler(context);
    }

    public long insertEtudiant(Etudiant e) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", e.getNom());
        values.put("prenom", e.getPrenom());
        values.put("email", e.getEmail());
        values.put("motdepasse", e.getMotDePasse());
        long id = db.insert("etudiant", null, values);
        db.close();
        Log.d("Insert", "Inserted student: " + e.getNom() + ", " + e.getPrenom() + ", " + e.getEmail());
        return id;
    }

    public Etudiant getEtudiantWithLogin(String email, String motdepasse) {
        Log.d("LoginQuery", "Searching for student with Email: " + email + " and Password: " + motdepasse);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("etudiant", null, "email=? AND motdepasse=?",
                new String[]{email, motdepasse}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Etudiant e = new Etudiant(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4));

            Log.d("LoginQuery", "Found student: " + e.getNom() + ", " + e.getPrenom());

            cursor.close();
            db.close();
            return e;
        } else {
            Log.d("LoginQuery", "No student found with the provided email and password.");
        }

        return null;
    }


    public List<Etudiant> getAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM etudiant", null);
        if (cursor.moveToFirst()) {
            do {
                etudiants.add(new Etudiant(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return etudiants;
    }

    public int deleteEtudiant(Etudiant e) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = db.delete("etudiant", "matricule=?",
                new String[]{String.valueOf(e.getMatricule())});
        db.close();
        return rowsDeleted;
    }
}
