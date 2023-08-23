package com.uog.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
//import meow.bottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {

    private String[] countries = {"England", "American", "Brazil"};

    private MeowBottomNavigation bottomNavigation;

    TextView txtDateTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));


        Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        long[] pattern = {0,150};


        txtDateTime = findViewById(R.id.txtDateTime);


        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
//        fade.excludeTarget(decor.findViewById(R.id.bottomNavigation),true)3;
        fade.excludeTarget(android.R.id.statusBarBackground,true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);


        TextView spinnertxt = findViewById(R.id.spinnertxt);



        bottomNavigation = findViewById(R.id.bottomNavigation);


        bottomNavigation.show(2,true);


        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.baseline_fastfood_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.baseline_add_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3 ,R.drawable.baseline_shopping_cart_24));
//
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()){

                    case 1:

                        vibrator.vibrate(pattern,-1);

//                        Remove Animation
//                        Intent intent = new Intent(MainActivity.this, Droid.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                        startActivityForResult(intent,0);
//                        overridePendingTransition(0,0);


//                        startActivity(new Intent(MainActivity.this, Droid.class));

                        break;

                  case 2:
                      vibrator.vibrate(pattern,-1);

//                      startActivity(new Intent(MainActivity.this, MainActivity.class));

                      break;

                  case 3:
                      vibrator.vibrate(pattern,-1);

//                      startActivity(new Intent(MainActivity.this, Cloud.class));

                      break;

                }

                return null;
            }
        });



//        Start Here

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
     spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             Log.i("MyName", countries[i]);

            spinnertxt.setText(countries[i]);

         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
     });


        Button btnShowDateTime = findViewById(R.id.btnShowDateTime);
        btnShowDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickFragment datePickFragment = new DatePickFragment();
                datePickFragment.show(getSupportFragmentManager(),"datePicker");
            }
        });

    }

        //Start Here

        public void setDate(LocalDate date){
            txtDateTime.setText(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        }




}