package com.example.pfe_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="pfe_db";
    public static final String TABLE_NAME="compte_user";
    public static final String COLONE_ID="id";
    public static final String COLONE_NOM="nom";
    public static final String COLONE_PRENOM="prenom";
    public static final String COLONE_TELE="telephone";
    public static final String COLONE_EMAIL="email";
    public static final String COLONE_PWD="pwd";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE compte_user ( id INTEGER PRIMARY  KEY AUTOINCREMENT, nom TEXT, prenom TEXT, telephone INTEGER, email TEXT, pwd TEXT );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public long addUser(String nom, String prenom, String telephone, String email, String pwd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nom",nom);
        contentValues.put("prenom",prenom);
        contentValues.put("telephone",telephone);
        contentValues.put("email",email);
        contentValues.put("pwd",pwd);
        long res = db.insert("compte_user",null,contentValues);
        db.close();
        return  res;
    }
    public boolean checkUser(String email, String pwd){
        String[] columns = { COLONE_ID };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLONE_EMAIL + "=?" + " and " + COLONE_PWD + "=?";
        String[] selectionArgs = { email, pwd };
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }

}
