package com.example.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab03.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.lab03.databinding.ActivitySecondBinding binding =
                ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textView = binding.tvActivity2TextEntered;
        Button button = binding.bActivity2Back;

        Intent extras = getIntent();
        Bundle b = extras.getExtras();
        String name = b.getString("enteredName");


        textView.setText(name);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}