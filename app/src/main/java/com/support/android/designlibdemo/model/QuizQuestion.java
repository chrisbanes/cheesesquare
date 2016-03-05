package com.support.android.designlibdemo.model;

import java.util.List;

public class QuizQuestion {
    int image;
    String question;
    List<String> answers;

    public QuizQuestion(int image, String question, List<String> answers) {
        this.image = image;
        this.question = question;
        this.answers = answers;
    }

    public int getImage() {
        return image;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
