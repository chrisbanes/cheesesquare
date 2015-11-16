package com.support.android.designlibdemo.views;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.support.android.designlibdemo.MainActivity;
import com.support.android.designlibdemo.R;

import org.junit.Rule;
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

    @Rule
    public ActivityTestRule<MainActivity> mRule = new ActivityTestRule<>(MainActivity.class);

    String appName = InstrumentationRegistry.getTargetContext().getString(R.string.app_name);

    String[] toolbarStringsIndex = {
            appName,
            "Category 1",
            "Category 2",
            "Category 3",

    };

    @Test
    public void toolbar_checkIfItHasAllSoughtStrings() {
        for (String text : toolbarStringsIndex) {
            onView(withText(text)).check(matches(isDisplayed()));

        }
    }


    @Test
    public void actionMenu_checkIfActionMenuHasSettingsValue() {
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
