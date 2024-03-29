package com.example.selfStudyApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.selfStudyApp.lib.QuestionData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "SelfStudyApp:Main";

    /** Data for the current question. */
    private QuestionData questionData = new QuestionData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up navigation bar
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
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

        //set welcome-screen values
        if (questionData.getQuestion() != null) {
            //Question
            TextView questionDisplay = findViewById(R.id.questionDisplay);
            questionDisplay.setText(questionData.getQuestion());

            //Answer
            List<String> possibleAnswers = questionData.getPossibleAnswers();
            if (possibleAnswers.size() > 0) {
                TextView answerDisplay = findViewById(R.id.answerDisplay);
                StringBuilder answerBuilder = new StringBuilder(possibleAnswers.get(0));
                for (int i = 1; i < possibleAnswers.size(); i++) {
                    answerBuilder.append("\n").append(possibleAnswers.get(i));
                }
                answerDisplay.setText(answerDisplay.toString());
            }
        }
    }

    /** Take the user to the About page. */
    private void goToAbout() {
        Log.d(TAG, "To About");
        setContentView(R.layout.content_about);
    }

    /** Take the user to the Set Question page. */
    private void goToSetQuestion() {
        Log.d(TAG, "To Set Question");
        setContentView(R.layout.content_set_question);

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
    private void goToSetAnswerNumber() {
        Log.d(TAG, "To Set Answer Number");
        setContentView(R.layout.content_set_answer_number);

        //the options for how many questions there are
        List<String> numberOptions = new ArrayList<>();
        for (int n = QuestionData.MIN_ANSWERS; n <= QuestionData.MAX_ANSWERS; n++) {
            numberOptions.add(Integer.toString(n));
        }

        //set up and populate spinner
        Spinner numberSpinner = findViewById(R.id.numberSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.content_set_answer_number, numberOptions);
        numberSpinner.setAdapter(adapter);

        //when number is chosen
        numberSpinner.setOnItemClickListener((parent, view, position, id) -> {
            String selection = (String) numberSpinner.getSelectedItem();
            int number = Integer.valueOf(selection);
            Button numberSelectButton = MainActivity.this.findViewById(R.id.numberSelectButton);
            numberSelectButton.setOnClickListener(w -> {
                if (number >= QuestionData.MIN_ANSWERS) {
                    MainActivity.this.goToSetAnswer(number);
                }
            });
        });
    }

    /** Take the user to the Set Answer page.
     *
     * @param numberQuestions how many questions to ask for
     */
    private void goToSetAnswer(int numberQuestions) {
        Log.d(TAG, "To Set Answer");
        setContentView(R.layout.content_set_answer);

        List<String> possibleAnswers = questionData.getPossibleAnswers();
        if (possibleAnswers.size() < numberQuestions) {
            setContentView(R.layout.content_set_answer);

            //set up answer input
            TextInputEditText answerInput = findViewById(R.id.answerInput);
            Button answerEntryButton = findViewById(R.id.answerEntryButton);

            //when answer is entered
            answerEntryButton.setOnClickListener(v -> {
                Editable text = answerInput.getText();
                if (text != null) {
                    possibleAnswers.add(text.toString());
                }
            });
        } else {
            questionData.setAnswers(possibleAnswers);
            goToSetQuestion();
        }
    }

    /** Take the user to the Answer Question page. */
    private void goToAnswerQuestion() {
        Log.d(TAG, "To Answer Question");
        setContentView(R.layout.content_answer_question);

        //show the question
        TextView questionDisplay = findViewById(R.id.questionDisplay);
        questionDisplay.setText(questionData.getQuestion());

        //set up and populate spinner
        Spinner answerSpinner = findViewById(R.id.answerSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.content_set_answer_number, questionData.getPossibleAnswers());
        answerSpinner.setAdapter(adapter);

        //when number is chosen
        answerSpinner.setOnItemClickListener((parent, view, position, id) -> {
            String selection = (String) answerSpinner.getSelectedItem();
            Button answerSelectButton = MainActivity.this.findViewById(R.id.answerSelectButton);
            answerSelectButton.setOnClickListener(w -> questionData.addAnswer(selection));
        });
    }

    /** Take the user to the View Data page. */
    private void goToViewData() {
        Log.d(TAG, "To View Data");
        setContentView(R.layout.content_view_data);
        //display data
    }
}
