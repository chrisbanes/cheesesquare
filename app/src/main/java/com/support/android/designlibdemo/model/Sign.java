package com.support.android.designlibdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Sign implements Parcelable {

    String title;
    int [] images;
    List<String> imageDescriptions;

    public Sign(String title, int[] images, List<String> imageDescription) {
        this.title = title;
        this.images = images;
        this.imageDescriptions = imageDescription;
    }

    protected Sign(Parcel in) {
        title = in.readString();
        images = in.createIntArray();
        imageDescriptions = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeIntArray(images);
        dest.writeStringList(imageDescriptions);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Sign> CREATOR = new Creator<Sign>() {
        @Override
        public Sign createFromParcel(Parcel in) {
            return new Sign(in);
        }

        @Override
        public Sign[] newArray(int size) {
            return new Sign[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public int[] getImages() {
        return images;
    }

    public List<String> getImageDescriptions() {
        return imageDescriptions;
    }

}
