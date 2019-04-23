package com.example.self_study_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "SelfStudyApp:Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create buttons
        final Button aboutButton = findViewById(R.id.aboutButton);
        final Button setQuestionButton = findViewById(R.id.setQuestionButton);
        final Button answerQuestionButton = findViewById(R.id.answerQuestionButton);
        final Button viewDataButton = findViewById(R.id.viewDataButton);

        //set buttons to start activity
        setQuestionButton.setOnClickListener(v -> {
            Log.d(TAG, "About button clicked");
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        });
        setQuestionButton.setOnClickListener(v -> {
            Log.d(TAG, "Set Question button clicked");
            Intent intent = new Intent(this, SetQuestion.class);
            startActivity(intent);
        });
        answerQuestionButton.setOnClickListener(v -> {
            Log.d(TAG, "Answer Question button clicked");
            Intent intent = new Intent(this, AnswerQuestion.class);
            startActivity(intent);
        });
        viewDataButton.setOnClickListener(v -> {
            Log.d(TAG, "View Data button clicked");
            Intent intent = new Intent(this, ViewData.class);
            startActivity(intent);
        });
    }
}
