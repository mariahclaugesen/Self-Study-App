package com.example.self_study_app;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "SelfStudyApp:Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up navigation bar
        final BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(v -> {
            int selectedId = bottomNav.getSelectedItemId();
            if (selectedId == R.id.navigation_about) {
                goToAbout();
            } else if (selectedId == R.id.navigation_set_question) {
                goToSetQuestion();
            } else if (selectedId == R.id.navigation_answer_question) {
                goToAnswerQuestion();
            } else {
                goToViewData();
            }
            return true;
        });

        //set up buttons (to remove later)
        final Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(v -> goToAbout());

        final Button setQuestionButton = findViewById(R.id.setQuestionButton);
        setQuestionButton.setOnClickListener(v -> goToSetQuestion());

        final Button answerQuestionButton = findViewById(R.id.answerQuestionButton);
        answerQuestionButton.setOnClickListener(v -> goToAnswerQuestion());

        final Button viewDataButton = findViewById(R.id.viewDataButton);
        viewDataButton.setOnClickListener(v -> goToViewData());
    }

    /** Take the user to the About page. */
    protected void goToAbout() {
        Log.d(TAG, "About button clicked");
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    /** Take the user to the Set Question page. */
    protected void goToSetQuestion() {
        Log.d(TAG, "Set Question button clicked");
        Intent intent = new Intent(this, SetQuestion.class);
        startActivity(intent);
    }

    /** Take the user to the Answer Question page. */
    protected void goToAnswerQuestion() {
        Log.d(TAG, "Answer Question button clicked");
        Intent intent = new Intent(this, AnswerQuestion.class);
        startActivity(intent);
    }

    /** Take the user to the View Data page. */
    protected void goToViewData() {
        Log.d(TAG, "View Data button clicked");
        Intent intent = new Intent(this, ViewData.class);
        startActivity(intent);
    }
}
