package com.example.quizmaster;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    int totalQuestions = 5;
    int currentQuestionIndex = 0;
    int score = 0;
    String selectedAnswer ;

    TextView question,questions,progressText;

    Button ansa,ansb,ansc,ansd,submitBtn,selectedAnswerBtn;
    Intent finalScreen;
    String name;
    int[] uniqueRandomNumbers = generateUniqueRandomNumbers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        // Retrieve the Intent that started this activity
        Intent intent = getIntent();

        // Extract the data from the Intent
         name = intent.getStringExtra("name");

        // Now you can use the value (name) as needed
        TextView textViewWelcomeUserName = findViewById(R.id.welcome_your_name);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(totalQuestions);
        textViewWelcomeUserName.setText("Welcome " + name);

        question = findViewById(R.id.question);
        questions = findViewById(R.id.questions);
        progressText = findViewById(R.id.progressText);
        ansa =  findViewById(R.id.ansa);
        ansb =  findViewById(R.id.ansb);
        ansc =  findViewById(R.id.ansc);
        ansd =  findViewById(R.id.ansd);
        submitBtn = findViewById(R.id.submit);
        ansa.setOnClickListener(this);
        ansb.setOnClickListener(this);
        ansc.setOnClickListener(this);
        ansd.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        finalScreen = new Intent(MainActivity2.this, MainActivity3.class);


        loadNewQuestion();
    }

    public static int[] generateUniqueRandomNumbers() {
        int[] randomNumbers = new int[5];
        Set<Integer> uniqueNumbers = new HashSet<>();
        Random random = new Random();
        while (uniqueNumbers.size() < 5) {
            int randomNumber = random.nextInt(20) + 1;
            uniqueNumbers.add(randomNumber);
        }
        int index = 0;
        for (int number : uniqueNumbers) {
            randomNumbers[index++] = number;
        }
        return randomNumbers;
    }


    void loadNewQuestion(){
        if(currentQuestionIndex==5){
            finalScreen.putExtra("name",name);
            finalScreen.putExtra("score",score);
            startActivity(finalScreen);
            return;
        }
        progressText.setText((currentQuestionIndex+1)+"/5");
        question.setText(AuestionAnswer.suggestions[uniqueRandomNumbers[ currentQuestionIndex]]);
        progressBar.setProgress(currentQuestionIndex+1);
        questions.setText(AuestionAnswer.questions[uniqueRandomNumbers[ currentQuestionIndex]]);
        ansa.setText(AuestionAnswer.choices[uniqueRandomNumbers[ currentQuestionIndex]][0]);
        ansb.setText(AuestionAnswer.choices[uniqueRandomNumbers[ currentQuestionIndex]][1]);
        ansc.setText(AuestionAnswer.choices[uniqueRandomNumbers[ currentQuestionIndex]][2]);
        ansd.setText(AuestionAnswer.choices[uniqueRandomNumbers[ currentQuestionIndex]][3]);
        selectedAnswer = null;
        if(currentQuestionIndex!=0){
            submitBtn.setText("Next");
        }
// Reset the background color of all buttons to indigo
        ansa.setBackgroundColor(ContextCompat.getColor(this, R.color.indigo));
        ansb.setBackgroundColor(ContextCompat.getColor(this, R.color.indigo));
        ansc.setBackgroundColor(ContextCompat.getColor(this, R.color.indigo));
        ansd.setBackgroundColor(ContextCompat.getColor(this, R.color.indigo));
    }

    @Override
    public void onClick(View view) {
        // Reset the background color of all buttons to indigo
        ansa.setBackgroundColor(ContextCompat.getColor(this, R.color.indigo));
        ansb.setBackgroundColor(ContextCompat.getColor(this, R.color.indigo));
        ansc.setBackgroundColor(ContextCompat.getColor(this, R.color.indigo));
        ansd.setBackgroundColor(ContextCompat.getColor(this, R.color.indigo));

        // Cast the clicked view to a Button
        Button clickedButton = (Button) view;

        // Handle the click event for the submit button
        if (view.getId() == R.id.submit) {
            // Check if an answer was selected
            if (selectedAnswer != null) {
                // Check if the selected answer is correct
                if (selectedAnswer.equals(AuestionAnswer.correctAnswers[uniqueRandomNumbers[ currentQuestionIndex]])) {
                    score++;
                    selectedAnswerBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.green));
                }else{
                    selectedAnswerBtn.setBackgroundColor(ContextCompat.getColor(this,R.color.red));
                }
                // Move to the next question after a delay
                clickedButton.setVisibility(View.INVISIBLE);
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        currentQuestionIndex++;
                        loadNewQuestion();
                        clickedButton.setVisibility(View.VISIBLE);
                    }
                }, 2000); // 2000 milliseconds delay
            } else {
                // Inform the user to select an answer
                Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
            }
        }else{

            // Update the selected answer
            selectedAnswer = clickedButton.getText().toString();
            selectedAnswerBtn = clickedButton;
            clickedButton.setBackgroundColor(ContextCompat.getColor(this,R.color.orange));

        }
    }




}