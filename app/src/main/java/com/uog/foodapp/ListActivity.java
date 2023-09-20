package com.uog.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    public  static final String[] WEEKDAYS = new String[]{"Mon","Tue","Wed"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView lv_ListView = findViewById(R.id.lv_ListView);
        ListAdapter adapter  = new ArrayAdapter(this,R.layout.list_item,WEEKDAYS);
        lv_ListView.setAdapter(adapter);


    }
}