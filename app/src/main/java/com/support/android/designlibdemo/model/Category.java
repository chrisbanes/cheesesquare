package com.support.android.designlibdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Category implements Parcelable {

    String title;
    String description;
    List<CategoryObject> items;
    List<Question> questions;

    public Category(String title, String description, List<CategoryObject> items, List<Question> questions) {
        this.title = title;
        this.description = description;
        this.items = items;
        this.questions = questions;
    }

    protected Category(Parcel in) {
        title = in.readString();
        description = in.readString();
        items = in.createTypedArrayList(CategoryObject.CREATOR);
        questions = in.createTypedArrayList(Question.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeTypedList(items);
        dest.writeTypedList(questions);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<CategoryObject> getItems() {
        return items;
    }

}
