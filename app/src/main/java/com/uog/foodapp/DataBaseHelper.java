package com.uog.foodapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.VisibleForTesting;

import com.uog.foodapp.adapter.PersonAdapter;
import com.uog.foodapp.database.Person;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="myDatabase.db";
    private static final String TABLE_PERSON ="tblPerson";

    public static final String PERSON_ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String AGE = "age";

    private SQLiteDatabase database;

    private static final String CREATE_PERSON_TABLE =String.format(
            "CREATE TABLE IF NOT EXISTS %s (" +
                    " %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s TEXT," +
                    " %s INTEGER)"
            , TABLE_PERSON, PERSON_ID, NAME, PHONE, ADDRESS, AGE);


    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
        database =getWritableDatabase();
        if(database !=null) database.execSQL( "PRAGMA encoding ='UTF-8'" );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PERSON_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long save(String name, String address, String phone, int age){
        long result =0;
        ContentValues rowValues =new ContentValues();
        rowValues.put(NAME, name);
        rowValues.put(ADDRESS, address);
        rowValues.put(PHONE, phone);
        rowValues.put(AGE, age);
        result =database.insertOrThrow(TABLE_PERSON, null, rowValues);
        return result;
    }


    public List search( String keyword ) throws Exception{
        Cursor cursor = null;
        String query ="SELECT * FROM " + TABLE_PERSON
                +" WHERE " + NAME +" LIKE '%" + keyword +"%'";

        List<Person> results =new ArrayList<>();
        cursor = database.rawQuery( query, null );
        cursor.moveToFirst( );
        while( !cursor.isAfterLast() ){
//            int id =cursor.getColumnIndex(PERSON_ID);
            int id =cursor.getInt(0);
            String name =cursor.getString(1);
            String address =cursor.getString(2);
            String phone =cursor.getString(3);
            int age =cursor.getInt(4);
            cursor.moveToNext( );

            Person person = new Person(id,name,address,phone,age);
            results.add(person);
        }
        cursor.close();
        return results;
    }

    public long delete(int id){
        long result = 0;
        String where = "id = ?";
        String valuse[] = {String.valueOf(id)};
        result  = database.delete(TABLE_PERSON,where,valuse);
        return  result;

    }


    public long update(int id, String name, String address, String phone, int age){
        long result =0;
        ContentValues rowValues =new ContentValues();
        rowValues.put(NAME, name);
        rowValues.put(ADDRESS, address);
        rowValues.put(PHONE, phone);
        rowValues.put(AGE, age);
        String where = "id = ?";
        String values[] = {id + ""};
        result =database.update(TABLE_PERSON,  rowValues, where, values);
        return result;
    }


}