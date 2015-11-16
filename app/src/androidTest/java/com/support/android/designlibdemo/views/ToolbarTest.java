package com.support.android.designlibdemo.views;

import android.support.test.espresso.matcher.ViewMatchers;

import com.support.android.designlibdemo.R;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by piotr on 16.11.15.
 */
public class ToolbarTest {
    @Test
    public void checkIfAppNameIsDisplayed() {
        onView(ViewMatchers.withText(R.string.app_name)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfActionMenuHasSettingsValue() {
        onView(withContentDescription("More options"))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withText(R.string.menu_settings)).check(matches(isDisplayed()));
    }

    @Test
    public void openNavigationDrawerByClick() {
        onView(withContentDescription("Navigate up"))
                .check(matches(isDisplayed()))
                .perform(click());
    }
}
