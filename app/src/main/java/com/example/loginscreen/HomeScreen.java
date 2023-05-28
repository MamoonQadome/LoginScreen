package com.example.loginscreen;

import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class HomeScreen extends AppCompatActivity {

    TextView tv;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);


        tv = findViewById(R.id.emailView);

        tv.setText("Welcome: "  );
    }
}
