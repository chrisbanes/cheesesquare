package com.support.android.designlibdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Category implements Parcelable {

    String name;
    List<CategoryObject> items;

    public Category(String name, List<CategoryObject> categories) {
        this.name = name;
        this.items = categories;
    }

    public String getName() {
        return name;
    }

    public List<CategoryObject> getItems() {
        return items;
    }

    protected Category(Parcel in) {
        name = in.readString();
        items = new ArrayList<>();
        in.readTypedList(items, CategoryObject.CREATOR);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(items);
    }
}
