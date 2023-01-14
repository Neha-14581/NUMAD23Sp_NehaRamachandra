package edu.northeastern.numad23sp_neharamachandra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //When the AboutMe button is clicked, the following thing should take place.
        //Proper button and the functionalities have been added to it.

        Button btn1 = findViewById(R.id.buttonAboutMe);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Neha Ramachandra\n ramachandra.n@northeastern.edu", Toast.LENGTH_SHORT)
                        .show();

            }
        });

    }
}