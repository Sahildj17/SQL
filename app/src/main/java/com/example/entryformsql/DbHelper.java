package com.example.entryformsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="from.db";
    public DbHelper(@Nullable Context context) {
                      //dbName
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q="create table student(name text primary key , email text , phone text)";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists student");
        onCreate(db);
    }
    public boolean insertData(String name,String email,String phone){
        SQLiteDatabase dbs=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("name",name);
        c.put("email",email);
        c.put("phone",phone);
        long r=dbs.insert("student",null,c);
        if(r==-1) return false;
        else{
            return true;
        }
    }
    public List<String> getData() {
        List<String> dataList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"name", "email", "phone"};
        Cursor cursor = db.query("student", projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));

            String entry = "Name: " + name + ", Email: " + email + ", Phone: " + phone;
            dataList.add(entry);
        }

        cursor.close();
        db.close();
        return dataList;
    }
}
