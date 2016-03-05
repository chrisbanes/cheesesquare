package com.support.android.designlibdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CategoryContainer implements Parcelable {

    String name;
    List<Category> categories;

    public CategoryContainer(String name, List<Category> categories) {
        this.name = name;
        this.categories = categories;
    }

    protected CategoryContainer(Parcel in) {
        name = in.readString();
        categories = in.createTypedArrayList(Category.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(categories);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CategoryContainer> CREATOR = new Creator<CategoryContainer>() {
        @Override
        public CategoryContainer createFromParcel(Parcel in) {
            return new CategoryContainer(in);
        }

        @Override
        public CategoryContainer[] newArray(int size) {
            return new CategoryContainer[size];
        }
    };

    public String getName() {
        return name;
    }

    public List<Category> getCategories() {
        return categories;
    }

}
