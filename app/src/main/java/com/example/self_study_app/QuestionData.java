package com.example.self_study_app;

import java.util.List;
import java.util.ArrayList;

/** A class for storing data related to one particular question. */
public class QuestionData {

    /** Minimum number of answers. */
    private final static int MIN_ANSWERS = 2;

    /** Maximum number of answers. */
    private final static int MAX_ANSWERS = 6;

    /** The question being asked. */
    private String question;

    /** The answers to the question. */
    private List<String> answers;

    /** The user's responses to the question. */
    private List<String> responses;

    /** Constructor which sets questions and answers.
     *
     * @param setQuestion what the question should be
     * @param setAnswers what the answers should be
     */
    QuestionData(final String setQuestion, final List<String> setAnswers) {
        if (setQuestion == null || setAnswers == null) {
            throw new IllegalArgumentException("inputs should not be null");
        }
        if (setAnswers.size() < MIN_ANSWERS || setAnswers.size() > MAX_ANSWERS) {
            throw new IllegalArgumentException("There should be between "
                    + MIN_ANSWERS + " and " + MAX_ANSWERS + " answers");
        }
        question = setQuestion;
        answers = setAnswers;
    }

    /** Add a response.
     *
     * @param response the response to add
     */
    public void addResponse(final String response) {
        if (!answers.contains(response)) {
            throw new IllegalArgumentException(response + " is not a valid answer");
        }
        responses.add(response);
    }

    /** Public getter for question.
     *
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /** Public getter for answers.
     *
     * @return answers
     */
    public List<String> getAnswers() {
        return answers;
    }

    /** Get the frequency of an answer.
     *
     * @param checkResponse the answer
     * @return how many times that answer appears
     */
    private int responseFrequency(final String checkResponse) {
        if (!answers.contains(checkResponse)) {
            throw new IllegalArgumentException(checkResponse + " is not a valid answer");
        }
        if (!responses.contains(checkResponse)) {
            return 0;
        }
        int count = 0;
        for (String response : responses) {
            if (checkResponse.equals(response)) {
                count++;
            }
        }
        return count;
    }

    //list of frequencies for each answer

    //chi-squared test for variance
}
