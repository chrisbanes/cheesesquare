package com.support.android.designlibdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CategoryObject implements Parcelable {

    String title;
    String fullDescription;
    int image;
    List<Question> questions;
    String linkToWebsite;
    Sign sign;

    public CategoryObject(String title, String fullDescription, int smallImage, List<Question> questions, String linkToWebsite) {
        this.title = title;
        this.fullDescription = fullDescription;
        this.image = smallImage;
        this.questions = questions;
        this.linkToWebsite = linkToWebsite;
    }

    public CategoryObject(String title, String fullDescription, int smallImage, List<Question> questions, String linkToWebsite, Sign sign) {
        this.title = title;
        this.fullDescription = fullDescription;
        this.image = smallImage;
        this.questions = questions;
        this.linkToWebsite = linkToWebsite;
        this.sign = sign;
    }

    protected CategoryObject(Parcel in) {
        title = in.readString();
        fullDescription = in.readString();
        image = in.readInt();
        questions = in.createTypedArrayList(Question.CREATOR);
        linkToWebsite = in.readString();
        sign = in.readParcelable(Sign.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(fullDescription);
        dest.writeInt(image);
        dest.writeTypedList(questions);
        dest.writeString(linkToWebsite);
        dest.writeParcelable(sign, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CategoryObject> CREATOR = new Creator<CategoryObject>() {
        @Override
        public CategoryObject createFromParcel(Parcel in) {
            return new CategoryObject(in);
        }

        @Override
        public CategoryObject[] newArray(int size) {
            return new CategoryObject[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getLinkToWebsite() {
        return linkToWebsite;
    }

    public int getImage() {
        return image;
    }

    public Sign getSign() {
        return sign;
    }

}
