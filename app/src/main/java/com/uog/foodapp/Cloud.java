package com.uog.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Cloud extends AppCompatActivity {



    private MeowBottomNavigation bottomNavigation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud);



        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.show(3,true);


        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_android_black_24dp));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.baseline_add_moderator_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3 ,R.drawable.baseline_cloud_download_24));
//
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()){

                    case 1:

                        startActivity(new Intent(Cloud.this, Droid.class));

                        break;

                    case 2:

                        startActivity(new Intent(Cloud.this, MainActivity.class));

                        break;

                    case 3:

//                        startActivity(new Intent(Cloud.this, Cloud.class));

                        break;

                }

                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()){

                    case 1:

                        startActivity(new Intent(Cloud.this, Droid.class));

                        break;


                }


                return null;
            }
        });bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()){


                    case 2:

                        startActivity(new Intent(Cloud.this, MainActivity.class));

                        break;


                }


                return null;
            }
        });bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()){


                    case 3:

//                        startActivity(new Intent(Cloud.this, Cloud.class));

                        break;

                }


                return null;
            }
        });


    }
}