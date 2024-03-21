package com.example.lab04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButtonToggleGroup buttonToggleGroup = findViewById(R.id.btg_theme);
        buttonToggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked)->{
            if (isChecked) {
                int theme = (checkedId == R.id.btg_light) ?
                        AppCompatDelegate.MODE_NIGHT_NO : AppCompatDelegate.MODE_NIGHT_YES;
                AppCompatDelegate.setDefaultNightMode(theme);
            }
        });
    }
}