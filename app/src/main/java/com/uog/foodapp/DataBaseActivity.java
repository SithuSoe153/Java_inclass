package com.uog.foodapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DataBaseActivity extends AppCompatActivity {

    private EditText txt_Name,txt_Phone, txt_Address, txt_Age;
    private Button btn_Save;

    private DataBaseHelper dataBaseHelper;

    private Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        txt_Name = findViewById(R.id.txt_Name);
        txt_Phone = findViewById(R.id.txt_Phone);
        txt_Address = findViewById(R.id.txt_Address);
        txt_Age = findViewById(R.id.txt_Age);

        btn_Save=findViewById(R.id.btn_Save);

        dataBaseHelper = new DataBaseHelper(getBaseContext());


        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            id=bundle.getInt(DataBaseHelper.PERSON_ID);
            txt_Name.setText(bundle.getString(DataBaseHelper.NAME));
            txt_Address.setText(bundle.getString(DataBaseHelper.ADDRESS));
            txt_Phone.setText(bundle.getString(DataBaseHelper.PHONE));
            txt_Age.setText(bundle.getInt(DataBaseHelper.AGE) + "");
        }

//        OnCLick
            btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               long result = 0;
               if (id == null){
                result = dataBaseHelper.save(txt_Name.getText().toString(),
                txt_Address.getText().toString(),
                txt_Phone.getText().toString(),
                Integer.parseInt(txt_Age.getText().toString()));

               } else if (id != null) {
                   result = dataBaseHelper.update(id,txt_Name.getText().toString(),
                           txt_Address.getText().toString(),
                           txt_Phone.getText().toString(),
                           Integer.parseInt(txt_Age.getText().toString()));

                           Intent intent = new Intent();
                           setResult(RESULT_OK,intent);
                           finish();

//                   Intent intent = new Intent(getBaseContext(), DataBaseListActivity.class);
//                   startActivity(intent);
               }


                if (result>0){
                   new AlertDialog.Builder(DataBaseActivity.this).setTitle("Data is stored").setMessage("Data successfully saved to database").show();

               }else{
                   new AlertDialog.Builder(DataBaseActivity.this).setTitle("Data cannot saved").setMessage("Failed to store data").show();

               }

                txt_Name.setText("");
                txt_Address.setText("");
                txt_Phone.setText("");
                txt_Age.setText("");

            }
        });



    }



}