package edu.northeastern.numad23sp_neharamachandra;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private Button button2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = (Button) findViewById(R.id.button2Clicky);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2Clicky();
            }
        });
    }

    public void openActivity2Clicky() {
        Intent intent = new Intent(this, Activity_Clicky_Final.class);
        startActivity(intent);
    }

    public void displayAboutMe(View view) {
        Intent intent1 = new Intent(this, AboutMeActivity.class);
        startActivity(intent1);
    }

    public void linkCollectorButton(View view) {
        Intent intent2 = new Intent(this, LinkCollector.class);
        startActivity(intent2);
    }
}