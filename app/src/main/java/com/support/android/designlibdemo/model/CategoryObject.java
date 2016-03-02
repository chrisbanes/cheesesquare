package com.support.android.designlibdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryObject implements Parcelable {

    int smallImage;
    int largeImage;
    String name;
    String description;

    public CategoryObject(int smallImage, int largeImage, String name, String description) {
        this.smallImage = smallImage;
        this.largeImage = largeImage;
        this.name = name;
        this.description = description;
    }

    protected CategoryObject(Parcel in) {
        smallImage = in.readInt();
        largeImage = in.readInt();
        name = in.readString();
        description = in.readString();
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

    public int getSmallImage() {
        return smallImage;
    }

    public int getLargeImage() {
        return largeImage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(smallImage);
        dest.writeInt(largeImage);
        dest.writeString(name);
        dest.writeString(description);
    }
}
