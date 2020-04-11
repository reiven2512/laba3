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
import static com.example.laba_3_2.Names.con.DB_CREATE_2;
import static com.example.laba_3_2.Names.con.FIRST;
import static com.example.laba_3_2.Names.con.ID;
import static com.example.laba_3_2.Names.con.LAST;
import static com.example.laba_3_2.Names.con.MIDDLE;
import static com.example.laba_3_2.Names.con.TABLE;
import static com.example.laba_3_2.Names.con.TEMP;

public class DbHelper extends SQLiteOpenHelper {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DbHelper(Context context) {
        super(context, "DataBase", null, 2);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_2);
        db.execSQL("alter table " + TEMP + " rename to " + TABLE);
        ContentValues cv = new ContentValues();
        for (int i = 1; i <= 5; i++) {
            String[] st = create_Fio().split(" ");
            cv.put(LAST, st[0]);
            cv.put(FIRST, st[1]);
            cv.put(MIDDLE, st[2]);
            cv.put(DATE, sdf.format(new Date()));
            db.insert(TABLE, null, cv);
        }
    }

    public void insert() {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        String[] st = create_Fio().split(" ");
        cv.put(LAST, st[0]);
        cv.put(FIRST, st[1]);
        cv.put(MIDDLE, st[2]);
        cv.put(DATE, sdf.format(new Date()));
        db.insert(TABLE, null, cv);
    }

    public void update() {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        cv.put(LAST, "Иванов");
        cv.put(FIRST, "Иван");
        cv.put(MIDDLE, "Иванович");
        Cursor c = db.query(TABLE, null, null, null, null, null,null);
        c.moveToLast();
        int last = c.getInt(c.getColumnIndex(ID));
        db.update(TABLE, cv, "id = ?",
                new String[]{Integer.toString(last)});
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion == 1 && newVersion == 2)
        {
            db.execSQL(DB_CREATE_2);
            Cursor c = db.query(TABLE, null, null, null, null, null, null);
            c.moveToFirst();
            ContentValues cv = new ContentValues();
            do{
                String[] s = c.getColumnNames();
                cv.put(ID, c.getInt(c.getColumnIndex(s[0])));
                String fio = c.getString(c.getColumnIndex(s[1]));
                String[] st = fio.split(" ");
                cv.put(LAST, st[0]);
                cv.put(FIRST, st[1]);
                cv.put(MIDDLE, st[2]);
                cv.put(DATE, c.getString(c.getColumnIndex(s[2])));
                db.insert(TEMP, null, cv);
            }while(c.moveToNext());
            db.execSQL("drop table " + TABLE);
            db.execSQL("Alter table " + TEMP +
                    " rename to " + TABLE);
        }
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
