package com.uog.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
//import meow.bottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {

    private String[] countries = {"England", "American", "Brazil"};

    private MeowBottomNavigation bottomNavigation;

    TextView txtDateTime;
    Button btn_Checkbox, btn_Read, btn_Save;
    CheckBox cb_checkbox;
    Toolbar tb_toolbar1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));


        Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        long[] pattern = {0,150};


        tb_toolbar1 = findViewById(R.id.tb_toolbar1);
        setSupportActionBar(tb_toolbar1);



        txtDateTime = findViewById(R.id.txtDateTime);
        btn_Checkbox = findViewById(R.id.btn_Checkbox);
        cb_checkbox = findViewById(R.id.cb_checkbox);

cb_checkbox.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        if (cb_checkbox.isChecked()){
            new AlertDialog.Builder(MainActivity.this).setTitle("Testing the checkbox").setMessage("Your Test CheckBox is Checked now!").show();
        }else{
            new AlertDialog.Builder(MainActivity.this).setTitle("Testing the checkbox").setMessage("Your Test CheckBox is not Checked!").show();
        }

    }
});



        btn_Save=findViewById(R.id.btn_Save);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Shared Preferences
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", "mg lay");
                editor.commit();


                try {
                    FileOutputStream outputStream= openFileOutput("my.txt", Context.MODE_PRIVATE);
                    PrintStream stream = new PrintStream(outputStream);
                    stream.println("Hello World");
                    stream.println("Hello Mom");

                    stream.close();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });




        btn_Read=findViewById(R.id.btn_Read);
        btn_Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                String name = sharedPreferences.getString("username","");

                Log.i("datasave", name);

//                File Read
                try {
                    FileInputStream inputStream = openFileInput("my.txt");
                    InputStreamReader streamReader = new InputStreamReader(inputStream);
                    BufferedReader reader = new BufferedReader(streamReader);
                    String line;


                    while ((line = reader.readLine()) !=null){
                            Log.i("MyName"  , line);
                    }

                    streamReader.close();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });




        btn_Checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb_checkbox.isChecked()){
                    Log.i("cb","Cb is checked");
                    new AlertDialog.Builder(MainActivity.this).setTitle("Testing the checkbox").setMessage("Your Test CheckBox is Checked now!").show();
                }else{
                    new AlertDialog.Builder(MainActivity.this).setTitle("Testing the checkbox").setMessage("Your Test CheckBox is not Checked!").show();
                    Log.i("cb","Cb is not checked");

                }
            }
        });

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

        Button btnShowDialog = findViewById(R.id.btnShowDialog);
        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Hello")
//                        .setMessage("How are you!")
                        .setPositiveButton("Confirm", null)
                        .setNegativeButton("No",null)
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        })
                      .show();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i("toolbox",item.getTitle().toString());
        if (item.getItemId() == R.id.tb_Exit){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setDate(LocalDate date){
        ZonedDateTime zdt = ZonedDateTime.now();

            txtDateTime.setText(zdt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm")));
        }




}