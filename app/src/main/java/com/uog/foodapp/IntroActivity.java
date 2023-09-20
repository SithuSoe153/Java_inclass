package com.uog.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.app.ActionBar;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.renderscript.Sampler;
import android.transition.Fade;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class IntroActivity extends AppCompatActivity {

    ImageView LogoBurger;
    View rectangle_bottom,rectangle;
    Animation fade_up,fade_down, from_right, fade;

    ConstraintLayout btnGetStarted, btnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_intro);

        LogoBurger = findViewById(R.id.LogoBurger);
//        fade = AnimationUtils.loadAnimation(this,R.anim.fade);
//        LogoBurger.setAnimation(fade);


        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.bottomNavigation),true);
        fade.excludeTarget(android.R.id.statusBarBackground,true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);




        Toast.makeText(IntroActivity.this,"Toast Tested", Toast.LENGTH_SHORT).show();

        rectangle_bottom= findViewById(R.id.rectangle_bottom);
        fade_up = AnimationUtils.loadAnimation(this,R.anim.fade_up);
        rectangle_bottom.setAnimation(fade_up);


        rectangle= findViewById(R.id.rectangle);
        fade_down = AnimationUtils.loadAnimation(this,R.anim.fade_down);
        rectangle.setAnimation(fade_down);

        btnGetStarted=findViewById(R.id.btnGetStarted);
        from_right = AnimationUtils.loadAnimation(this,R.anim.from_right);
        btnGetStarted.setAnimation(from_right);

        btnQuit=findViewById(R.id.btnQuit);
        from_right = AnimationUtils.loadAnimation(this,R.anim.from_right);
        btnQuit.setAnimation(from_right);

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity.this, DataBaseListActivity.class);
                startActivity(intent);
            }
        });



//        rectangle.animate().translationY(-1400).setDuration(2700).setStartDelay(0);

         Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
         long[] pattern = {0,150};


        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                vibrator.vibrate(pattern,-1);


//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

//                ActivityOptionsCompat options =ActivityOptionsCompat.makeSceneTransitionAnimation(IntroActivity.this, rectangle, ViewCompat.getTransitionName(rectangle));
                Pair[] pair = new Pair[2];
                pair[0] = new Pair<>(LogoBurger, "example_burger");
                pair[1] = new Pair<>(rectangle, "example_transition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(IntroActivity.this,pair);

                Intent intent = new Intent(IntroActivity.this, DataBaseActivity.class);

            startActivity(intent,options.toBundle());


            //                startActivityForResult(intent,0);

//                overridePendingTransition(0,0);

            }

        });




    }
}