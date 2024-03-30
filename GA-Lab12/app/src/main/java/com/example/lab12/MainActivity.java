package com.example.lab12;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.megalovania);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            if (!clicked) {
                mediaPlayer.start();
                Snackbar.make(v, "Your should hear a sound", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            } else {
                mediaPlayer.pause();
                Snackbar.make(v, "Your should stop hearing the sound", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            clicked = !clicked;
        }
    }
}