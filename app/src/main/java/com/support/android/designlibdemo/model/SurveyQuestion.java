package com.support.android.designlibdemo.model;

import java.util.List;

public class SurveyQuestion {
    String sQuestion;
    List<String> answers;

    public SurveyQuestion(String sQuestion, List<String> answers) {
        this.sQuestion = sQuestion;
        this.answers = answers;
    }

    public String getsQuestion() {
        return sQuestion;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
