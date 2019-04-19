package com.example.self_study_app;

import java.util.List;
import java.util.ArrayList;

/** A class for storing data related to one particular question. */
public class QuestionData {

    /** Minimum number of answers. */
    private final static int MIN_ANSWERS = 2;

    /** Maximum number of answers. Can change if needed. */
    private final static int MAX_ANSWERS = 6;

    /** The question being asked. */
    private String question;

    /** The available answers to the question. */
    private List<String> possibleAnswers;

    /** The user's choices of answers to the question. */
    private List<String> chosenAnswers = new ArrayList<>(0);

    /** The frequencies of each answer. */
    private List<Frequency> frequencies;

    /** Constructor which sets questions and answers.
     *
     * @param setQuestion what the question should be
     * @param setAnswers what the answers should be
     */
    QuestionData(final String setQuestion, final List<String> setAnswers) {
        if (setQuestion == null || setAnswers == null || setAnswers.contains(null)) {
            throw new IllegalArgumentException("inputs should not be null");
        }
        if (setAnswers.size() < MIN_ANSWERS || setAnswers.size() > MAX_ANSWERS) {
            throw new IllegalArgumentException("There should be between "
                    + MIN_ANSWERS + " and " + MAX_ANSWERS + " answers");
        }
        question = setQuestion;
        possibleAnswers = setAnswers;
        setFrequencies();
    }

    /** Add a response.
     *
     * @param answer the response to add
     */
    public void addAnswer(final String answer) {
        if (!possibleAnswers.contains(answer)) {
            throw new IllegalArgumentException(answer + " is not a valid answer");
        }
        chosenAnswers.add(answer);
        setFrequency(answer);
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
    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    /** Do a chi-squared test for variance.
     *
     * @return the p-value
     */
    public double chiSquaredVariance() {
        return 0;
    }

    private double averageFrequency() {
        double sum = 0;
        for (Frequency frequency : frequencies) {
            sum += frequency.getFrequency();
        }
        return sum / (double) frequencies.size();
    }

    /** Recalculate frequencies.
     *
     */
    private void setFrequencies() {
        frequencies = new ArrayList<>(possibleAnswers.size());
        for (int i = 0; i < possibleAnswers.size(); i++) {
            frequencies.add(new Frequency(possibleAnswers.get(i)));
        }
    }

    /** Recalculate the frequency of one answer.
     *
     * @param answer the answer to recalculate for
     */
    private void setFrequency(final String answer) {
        for (Frequency frequency : frequencies) {
            if (frequency.getAnswer().equals(answer)) {
                frequency.setFrequency();
            }
        }
    }

    /** Public getter for frequencies.
     *
     * @return frequencies
     */
    public List<Frequency> getFrequencies() {
        return frequencies;
    }

    /** Interior class that stores answers and their frequencies.
     *
     */
    public class Frequency {
        /** The answer. */
        private String answer;

        /** The answer's frequency. */
        private int frequency;

        /** Constructor.
         *
         * @param setAnswer the answer
         */
        Frequency(final String setAnswer) {
            if (!possibleAnswers.contains(setAnswer)) {
                throw new IllegalArgumentException(setAnswer + " is not a valid answer");
            }
            answer = setAnswer;
            setFrequency();
        }

        /** Set the frequency for this answer. */
        void setFrequency() {
            frequency = 0;
            if (chosenAnswers.contains(answer)) {
                for (String chosenAnswer : chosenAnswers) {
                    if (chosenAnswer.equals(answer)) {
                        frequency++;
                    }
                }
            }
        }

        /** Public getter for answer.
         *
         * @return answer
         */
        public String getAnswer() {
            return answer;
        }

        /** Public getter for frequency.
         *
         * @return frequency
         */
        public int getFrequency() {
            return frequency;
        }
    }
}
