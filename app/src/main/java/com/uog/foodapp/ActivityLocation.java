package com.uog.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class ActivityLocation extends AppCompatActivity {

    private FusedLocationProviderClient locationClient;
    private final int REQUEST_PERMISSION_FINE_LOCATION = 1;

    private TextView txt_location;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_FINE_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(ActivityLocation.this, "Permission Granted!", Toast.LENGTH_LONG).show();
                    showLocation();
                } else {
                    Toast.makeText(ActivityLocation.this, "Permission Denied!", Toast.LENGTH_LONG).show();
                    txt_location.setText("Location permission not granted");
                }
        }
    }

    @SuppressLint("MissingPermission")
    private void showLocation() {
        locationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {

                    txt_location.setText("Current location is:\nLat:" + location.getLatitude()
                            + "\nLon: " + location.getLongitude());
                }else{  txt_location.setText("Good");}
            }
        });

        locationClient.getLastLocation().addOnCompleteListener(this, new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if(task.isSuccessful()) {
                    Location location = task.getResult();
                    if (location != null) {

                        txt_location.setText("Current location is:\nLat:" + location.getLatitude()
                                + "\nLon: " + location.getLongitude());
                    }
                    else {
                        txt_location.setText("Problem getting the location");
                    }
                }

            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationClient = LocationServices.getFusedLocationProviderClient(this);
        txt_location = ((TextView)findViewById(R.id.txt_location));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_FINE_LOCATION);
        } else {
            showLocation();
        }
    }
}