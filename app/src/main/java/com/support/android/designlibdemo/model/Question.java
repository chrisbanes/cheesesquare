package com.support.android.designlibdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    String question;
    String answer;

    public Question(String pitanje, String odgovor) {
        this.question = pitanje;
        this.answer = odgovor;
    }

    protected Question(Parcel in) {
        question = in.readString();
        answer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
