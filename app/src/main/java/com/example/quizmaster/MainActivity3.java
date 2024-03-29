package com.example.quizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    TextView your_score_point,congratulation_your_name;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
// Retrieve the Intent that started this activity
        Intent intent = getIntent();

        // Extract the data from the Intent
         name = intent.getStringExtra("name");
        int score = intent.getIntExtra("score",0);
        your_score_point=findViewById(R.id.your_score_point);
        congratulation_your_name=findViewById(R.id.congratulation_your_name);
        your_score_point.setText(score+"/"+"5");
        if(score<3){
            congratulation_your_name.setText("Opps try again "+name);
        }else{
            congratulation_your_name.setText("Congratulation "+name);
        }
    }
    public void onFinishClick(View view) {
        finishAffinity();
    }
    public void  onTakeNewQuizClick(View view){
        Intent FirstActivity = new Intent(MainActivity3.this, MainActivity.class);
        FirstActivity.putExtra("name",name);
        startActivity(FirstActivity);

    }
    }