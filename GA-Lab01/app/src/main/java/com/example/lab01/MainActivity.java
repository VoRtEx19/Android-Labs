package com.example.lab01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button hello;
    TextView main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hello = findViewById(R.id.hello_button);
        main = findViewById(R.id.main_textView);

        hello.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        main.setText(R.string.hello_string);
        hello.setClickable(false);
        hello.setVisibility(View.INVISIBLE);
    }
}