package com.example.lab13;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        VideoView videoView = findViewById(R.id.videoView);
        Log.d("Video", Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.anakin).toString());
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.anakin));
        videoView.setMediaController(new MediaController(this));
        videoView.start();
        videoView.requestFocus();
    }
}