package com.example.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab03.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String name;

    private EditText editText;
    private Button button_next;
    private Button button_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.lab03.databinding.ActivityMainBinding binding =
                ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editText = binding.etActivity1EnterName;
        button_next = binding.bActivity1Next;
        button_exit = binding.bActivity1Exit;

        editText.setText(name);
        button_next.setOnClickListener(this);
        button_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button_next) {
            name = editText.getText().toString();
            Bundle bundle = new Bundle();
            bundle.putString("enteredName", name);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (v == button_exit) {
            finish();
        }
    }
}