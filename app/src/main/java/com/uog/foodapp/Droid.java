package com.uog.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Droid extends AppCompatActivity {


    private MeowBottomNavigation bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droid);




        bottomNavigation = findViewById(R.id.bottomNavigation);




//        Fade fade = new Fade();
//        View decor = getWindow().getDecorView();
////        fade.excludeTarget(decor.findViewById(R.id.action_containe),true);
//        fade.excludeTarget(android.R.id.statusBarBackground,true);
//        fade.excludeTarget(android.R.id.navigationBarBackground, true);
//
//        getWindow().setEnterTransition(fade);
//        getWindow().setExitTransition(fade);



        bottomNavigation.show(1,true);


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

//                        startActivity(new Intent(Droid.this, Droid.class));

                        break;

                    case 2:

//                        startActivity(new Intent(Droid.this, MainActivity.class));

                        Intent intent = new Intent(Droid.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivityForResult(intent,0);
                        overridePendingTransition(0,0);


                        break;

                    case 3:

                        startActivity(new Intent(Droid.this, Cloud.class));

                        break;

                }

                return null;
            }
        });





    }
}