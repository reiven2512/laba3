package com.example.laba_3_2;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.laba_3_2.Names.con.DATE;
import static com.example.laba_3_2.Names.con.FIO;
import static com.example.laba_3_2.Names.con.FIRST;
import static com.example.laba_3_2.Names.con.ID;
import static com.example.laba_3_2.Names.con.LAST;
import static com.example.laba_3_2.Names.con.MIDDLE;
import static com.example.laba_3_2.Names.con.TABLE;

public class ShowActivity extends AppCompatActivity {
    DbHelper dbHelper;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);
        dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(TABLE,null, null, null, null, null, null);
        c.moveToFirst();
        if(db.getVersion() == 1)
        {
            add_row(ID, FIO, DATE, null, null);
            do {
                String[] s = c.getColumnNames();
                int id = c.getInt(c.getColumnIndex(s[0]));
                String fio = c.getString(c.getColumnIndex(s[1]));
                String date = c.getString(c.getColumnIndex(s[2]));
                add_row(Integer.toString(id), fio, date, null, null);
            } while (c.moveToNext());
        }
        else {
            add_row(ID, LAST, FIRST, MIDDLE, DATE);
            do {
                String[] s = c.getColumnNames();
                int id = c.getInt(c.getColumnIndex(s[0]));
                String last = c.getString(c.getColumnIndex(s[1]));
                String first = c.getString(c.getColumnIndex(s[2]));
                String middle = c.getString(c.getColumnIndex(s[3]));
                String date = c.getString(c.getColumnIndex(s[4]));
                add_row(Integer.toString(id), last, first, middle, date);
            } while (c.moveToNext());
        }
    }
    public void add_row(String id, String last, String first, String middle, String time){
        TableLayout tl = (TableLayout) findViewById(R.id.layout);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.row, null);
        TextView tv = (TextView) tr.findViewById(R.id.name_1);
        tv.setText(id);
        tv = (TextView) tr.findViewById(R.id.name_2);
        tv.setText(last);
        tv = (TextView) tr.findViewById(R.id.name_3);
        tv.setText(first);
        tv = (TextView) tr.findViewById(R.id.name_4);
        tv.setText(middle);
        tv = (TextView) tr.findViewById(R.id.name_5);
        tv.setText(time);
        tl.addView(tr);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        dbHelper.close();
    }
}

