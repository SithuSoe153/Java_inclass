package com.uog.foodapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.uog.foodapp.adapter.PersonAdapter;
import com.uog.foodapp.database.Person;

import java.util.ArrayList;
import java.util.List;

public class DataBaseListActivity extends AppCompatActivity {
private List<Person> personList = new ArrayList<>();

private  DataBaseHelper dataBaseHelper;
private PersonAdapter personAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_list);

        dataBaseHelper = new DataBaseHelper(getBaseContext());

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personAdapter = new PersonAdapter(personList);
        //
        personAdapter.setListener(new PersonAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v, long id) {
                if (id==R.id.btn_Remove){

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                           try {
                               Person person = personList.get(position);
//                               remove
                               dataBaseHelper.delete(person.getId());
//                               List
                               personList = dataBaseHelper.search("");
                               personAdapter.setPersonList(personList);
                               personAdapter.notifyDataSetChanged(); // refresh the data
                           }catch (Exception e){

                           }

                        }
                    });


                } else if (id == R.id.btn_Edit) {
                    Person person = personList.get(position);
                    Intent intent = new Intent(getBaseContext(),DataBaseActivity.class);
                    intent.putExtra(DataBaseHelper.PERSON_ID,person.getId());
                    intent.putExtra(DataBaseHelper.NAME,person.getName());
                    intent.putExtra(DataBaseHelper.ADDRESS,person.getAddress());
                    intent.putExtra(DataBaseHelper.PHONE,person.getPhone());
                    intent.putExtra(DataBaseHelper.AGE,person.getAge());


                    startActivityForResult(intent,UPDATE_RESULT);

                }
            }
        });
        recyclerView.setAdapter(personAdapter);


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                try {

                    personList = dataBaseHelper.search("");
                    personAdapter.setPersonList(personList);
                    personAdapter.notifyDataSetChanged(); // refresh the data

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });




    }

    public static final int UPDATE_RESULT = 123;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == UPDATE_RESULT && resultCode == RESULT_OK){

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try {

                        personList = dataBaseHelper.search("");
                        personAdapter.setPersonList(personList);
                        personAdapter.notifyDataSetChanged(); // refresh the data

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            });

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}