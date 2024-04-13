package com.example.lab16;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String tkn = FirebaseInstanceId.getInstance().getToken();
        Toast.makeText(MainActivity.this, "Current token ["+tkn+"]",
                Toast.LENGTH_LONG).show();
        Log.d("App", "Token ["+tkn+"]");
    }
}