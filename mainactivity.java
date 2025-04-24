package com.example.guidedexercise1to10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    EditText name, age;
    TextView result;
    Button click;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        layout = findViewById(R.id.main);
        name = findViewById(R.id.etNameGE1);
        age = findViewById(R.id.etAgeGE1);
        result = findViewById(R.id.tvResultGE1);
        click = findViewById(R.id.btnClickGE1);

        // Button to open second page
        Button btnNext = findViewById(R.id.btnNextPage);
        btnNext.setOnClickListener(this::onClick);

        showResult();
    }

    @SuppressLint("SetTextI18n")
    public void showResult() {
        click.setOnClickListener(view -> {
            String inputName = name.getText().toString().trim();
            String inputAge = age.getText().toString().trim();

            if (inputName.isEmpty()) {
                result.setText("Please enter your name.");
                name.requestFocus();
                return;
            }

            if (!inputName.matches("[a-zA-Z ]+")) {
                result.setText("Name must contain only letters.");
                name.requestFocus();
                return;
            }

            if (inputAge.isEmpty()) {
                result.setText("Please enter your age.");
                age.requestFocus();
                return;
            }

            if (!inputAge.matches("\\d+")) {
                result.setText("Age must be a number.");
                age.requestFocus();
                return;
            }

            result.setText("Name: " + inputName + "\nAge: " + inputAge);
            name.setText("");
            age.setText("");
            name.requestFocus();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            int selectedColor = data.getIntExtra("selectedColor", Color.WHITE);
            layout.setBackgroundColor(selectedColor);
        }
    }

    private void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivityForResult(intent, 1);
    }
}
