package com.example.lab_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab_02.databinding.ActivityEnterBinding;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.lab_02.databinding.ActivityEnterBinding binding = ActivityEnterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText enter = binding.enterEdit;
        final Button button = binding.submitButton;
        final TextView show = binding.resultTextview;

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable editable) {
                show.setText(enter.getText().toString());
            }
        };
        enter.addTextChangedListener(afterTextChangedListener);

        button.setOnClickListener(v -> {
            if (show.getText().toString().isEmpty()) {
                Toast.makeText(EnterActivity.this,
                        "You have entered nothing", Toast.LENGTH_SHORT).show();
                return;
            }
            if (button.getText().toString().equals("Submit")) {
                button.setText(R.string.change_string);
                enter.setVisibility(View.INVISIBLE);
                enter.setClickable(false);
                show.setVisibility(View.VISIBLE);
                show.setClickable(true);
            } else {
                button.setText(R.string.submit_string);
                enter.setVisibility(View.VISIBLE);
                enter.setClickable(true);
                show.setVisibility(View.INVISIBLE);
                show.setClickable(false);
            }
        });
    }
}