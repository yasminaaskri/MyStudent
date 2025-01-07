package com.example.mystudent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BDD_Etudiant";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ETUDIANT = "etudiant";
    private static final String KEY_MATRICULE = "matricule";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM = "prenom";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOTDEPASSE = "motdepasse";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ETUDIANT_TABLE = "CREATE TABLE " + TABLE_ETUDIANT + "(" +
                KEY_MATRICULE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NOM + " TEXT," +
                KEY_PRENOM + " TEXT," +
                KEY_EMAIL + " TEXT," +
                KEY_MOTDEPASSE + " TEXT" + ")";
        db.execSQL(CREATE_ETUDIANT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ETUDIANT);
        onCreate(db);
    }
}
