package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lab05.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button listButton, recyclerButton, gridButton, exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.lab05.databinding.ActivityMainBinding binding =
                ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listButton = binding.mainListButton;
        recyclerButton = binding.mainRecyclerButton;
        gridButton = binding.mainGridButton;
        exitButton = binding.mainExitButton;

        listButton.setOnClickListener(this);
        recyclerButton.setOnClickListener(this);
        gridButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v == listButton) {
            intent = new Intent(MainActivity.this, ListViewActivity.class);
            startActivity(intent);
        } else if (v == recyclerButton) {
            intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
            startActivity(intent);
        } else if (v == gridButton) {
            intent = new Intent(MainActivity.this, GridViewActivity.class);
            startActivity(intent);
        } else {
            finish();
        }
    }
}