package com.support.android.designlibdemo.model;

import com.support.android.designlibdemo.R;

import java.util.Arrays;

public class FinalData {

    static CategoryObject o1 = new CategoryObject(R.drawable.cheese_1, R.drawable.cheese_1, "Sir", "desc");
    static CategoryObject o2 = new CategoryObject(R.drawable.cheese_2, R.drawable.cheese_2, "Sirac", "desc");
    static CategoryObject o3 = new CategoryObject(R.drawable.cheese_3, R.drawable.cheese_3, "Kajmak", "desc");
    static CategoryObject o4 = new CategoryObject(R.drawable.cheese_4, R.drawable.cheese_4, "Kajmak", "desc");

    static Category cat1 = new Category("Prva kat", Arrays.asList(o1, o2, o3));
    static Category cat2 = new Category("Druga kat", Arrays.asList(o1, o4));
    public static final CategoryContainer one = new CategoryContainer("jedan", Arrays.asList(cat1, cat2));

}
