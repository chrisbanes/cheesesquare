package com.support.android.designlibdemo.view;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.support.android.designlibdemo.MainActivity;
import com.support.android.designlibdemo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * @author piotr on 16.11.15.
 */
@RunWith(AndroidJUnit4.class)
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
    public void toolbar_collapseToolbar() {
        onView(withId(R.id.toolbar)).perform(swipeUp())
                .check(matches(not(isCompletelyDisplayed())));

    }

    @Test
    public void actionMenu_checkIfActionMenuHasSettingsValue() {
        onView(withContentDescription("More options"))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withText(R.string.menu_settings)).check(matches(isDisplayed()));

    }

    @Test
    public void navigationDrawer_openNavigationDrawerByClick() {
        onView(withContentDescription("Navigate up"))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withText("Username")).check(matches(isDisplayed()));

    }

    @Test
    public void navigationDrawer_openNavigationDrawerBySwipeRight() {
        for (String index : toolbarStringsIndex) {
            if (index == appName) continue;
            onView(withText(index)).check(matches(isDisplayed())).perform(click());
        }

    }
}
