package com.example.selfStudyApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;

import com.example.selfStudyApp.lib.QuestionData;

public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "SelfStudyApp:Main";

    /** Data for the current question. */
    protected QuestionData questionData;

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
    }

    /** Take the user to the About page. */
    protected void goToAbout() {
        Log.d(TAG, "About button clicked");
        setContentView(R.layout.activity_about);
    }

    /** Take the user to the Set Question page. */
    protected void goToSetQuestion() {
        Log.d(TAG, "Set Question button clicked");
        setContentView(R.layout.activity_set_question);

        //set up question input
        TextInputEditText questionInput = findViewById(R.id.questionInput);
        Button questionEntryButton = findViewById(R.id.questionEntryButton);

        //when question is entered
        questionEntryButton.setOnClickListener(v -> {
            Editable text = questionInput.getText();
            if (text != null) {
                questionData = new QuestionData(text.toString());
                goToSetAnswerNumber();
            }
        });
    }

    /** Take the user to the Set Answer Number page. */
    protected void goToSetAnswerNumber() {

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
