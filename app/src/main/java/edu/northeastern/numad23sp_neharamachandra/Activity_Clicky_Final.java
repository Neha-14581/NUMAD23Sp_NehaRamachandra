package edu.northeastern.numad23sp_neharamachandra;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Clicky_Final extends AppCompatActivity {

    private TextView textToDisplay;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_final);
        textToDisplay = findViewById(R.id.pressedText);
        textToDisplay.setText("Pressed: - ");
    }
    //Click button was added

    public void onClick(View view) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Pressed: ");
        stringBuilder.append(((Button) view).getText().toString());
        textToDisplay.setText(stringBuilder.toString());
    }
}