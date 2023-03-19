package edu.northeastern.numad23sp_neharamachandra;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LocationActivity extends AppCompatActivity implements LocationListener {

    TextView currLatitude;
    TextView currLongitude;
    TextView totalDistance;

    protected LocationManager locationManager;

    private Location lastLocation;

    Button resetDistance;
    protected Context context;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        currLatitude = findViewById(R.id.latitude_val);
        currLongitude = findViewById(R.id.longitude_val);
        totalDistance = findViewById(R.id.distance_text);
        resetDistance = findViewById(R.id.reset_distance);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 50, this);
            lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        resetDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(LocationActivity.this)
                        .setMessage("Are you sure you want reset the total distance?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                totalDistance.setText("0 m");
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 50, this);
                        lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }else{
                    new AlertDialog.Builder(this)
                            .setMessage("This activity requires location services to work. Please check your settings and try again.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    finish();
                                }})
                            .show();
                }
                return;
            }
        }
    }

    @Override
    public void onLocationChanged(Location newLocation) {
        currLatitude.setText( newLocation.getLatitude() + "");
        currLongitude.setText(newLocation.getLongitude() + "");

        if(lastLocation != null){
            totalDistance.setText(Math.round(lastLocation.distanceTo(newLocation)) + " m");
        }
        lastLocation = newLocation;
    }

    @Override
    public void onBackPressed() {
        if (totalDistance.getText() != "0 m"){
            new AlertDialog.Builder(this)
                    .setMessage("Exit will reset total distance!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            totalDistance.setText("0m");
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    })
                    .show();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}
