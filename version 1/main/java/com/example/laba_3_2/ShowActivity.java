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
import static com.example.laba_3_2.Names.con.ID;
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
        add_row(ID, FIO, DATE);
        do {
            String[] s = c.getColumnNames();
            int id = c.getInt(c.getColumnIndex(s[0]));
            String fio = c.getString(c.getColumnIndex(s[1]));
            String date = c.getString(c.getColumnIndex(s[2]));
            add_row(Integer.toString(id), fio, date);
        } while (c.moveToNext());
    }
    public void add_row(String id, String fio, String time){
        TableLayout tl = (TableLayout) findViewById(R.id.layout);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.row, null);
        TextView tv = (TextView) tr.findViewById(R.id.name_1);
        tv.setText(id);
        tv = (TextView) tr.findViewById(R.id.name_2);
        tv.setText(fio);
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

