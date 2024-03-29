package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import  android.widget.EditText;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    EditText editText; // Declare EditText without initializing it here
    Intent intent;
    Intent lastActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize EditText and Intent after setContentView()
        editText = findViewById(R.id.edit_your_name);
        intent = new Intent(MainActivity.this, MainActivity2.class);
        lastActivity = getIntent();
        String name = lastActivity.getStringExtra("name");
        if (name != null && !name.isEmpty()) {
            editText.setText(name);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to handle button click
    public void onStartButtonClick(View view) {
        if (!editText.getText().toString().isEmpty()) {
            intent.putExtra("name", editText.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
        }
    }
}
