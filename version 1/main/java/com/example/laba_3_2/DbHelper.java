package com.example.laba_3_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.example.laba_3_2.Names.con.DATE;
import static com.example.laba_3_2.Names.con.DB_CREATE;
import static com.example.laba_3_2.Names.con.FIO;
import static com.example.laba_3_2.Names.con.ID;
import static com.example.laba_3_2.Names.con.TABLE;

public class DbHelper extends SQLiteOpenHelper {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DbHelper(Context context) {
        super(context, "DataBase", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
        ContentValues cv = new ContentValues();
        for (int i = 1; i <= 5; i++) {
            cv.put(FIO, create_Fio());
            cv.put(DATE, sdf.format(new Date()));
            db.insert(TABLE, null, cv);
        }
    }
    public void recreate_table(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("drop table " + TABLE);
        db.execSQL(DB_CREATE);
        ContentValues cv = new ContentValues();
        for (int i = 1; i <= 5; i++) {
            cv.put(FIO, create_Fio());
            cv.put(DATE, sdf.format(new Date()));
            db.insert(TABLE, null, cv);
        }
    }

    public void insert() {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIO, create_Fio());
        cv.put(DATE, sdf.format(new Date()));
        db.insert(TABLE, null, cv);
    }

    public void update() {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        cv.put(FIO, "Иванов Иван Иванович");
        Cursor c = db.query(TABLE, null, null, null, null, null,null);
        c.moveToLast();
        int last = c.getInt(c.getColumnIndex(ID));
        db.update(TABLE, cv, "id = ?",
                new String[]{Integer.toString(last)});
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      
    }

    public String create_Fio() {
        Random rd = new Random();
        String s = "abcdefghijklmnopqrstuvwxyz";
        String fin = "";
        int rand = rd.nextInt(5) + 3;
        for (int j = 0; j < rand; j++) {
            int l = rd.nextInt(s.length());
            fin += s.charAt(l);
        }
        fin += " ";
        rand = rd.nextInt(5) + 3;
        for (int j = 0; j < rand; j++) {
            int l = rd.nextInt(s.length());
            fin += s.charAt(l);
        }
        fin += " ";
        rand = rd.nextInt(5) + 3;
        for (int j = 0; j < rand; j++) {
            int l = rd.nextInt(s.length());
            fin += s.charAt(l);
        }
        return fin;
    }
}
