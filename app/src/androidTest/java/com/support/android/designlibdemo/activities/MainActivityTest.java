/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.support.android.designlibdemo.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.support.android.designlibdemo.MainActivity;
import com.support.android.designlibdemo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringEndsWith.endsWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String SNACKBAR = "Snackbar";

    @Rule
    public ActivityTestRule<MainActivity> mRule = new ActivityTestRule<>(MainActivity.class);

    int[] mainActivityViewsIndex = {
            R.id.drawer_layout,
            R.id.main_content,
            R.id.appbar,
            R.id.toolbar,
            R.id.tabs,
            R.id.viewpager,
            R.id.fab,
    };

    @Test
    public void activity_checkIfAppNameIsDisplayed() {
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
    }

    @Test
    public void activity_checkIfMainActivityViewsAreDisplayed() {
        for (int index : mainActivityViewsIndex) {
            onView(withId(index)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void activity_checkIfNavViewIsNotDisplayed() {
        onView(withId(R.id.nav_view)).check(matches(not(isDisplayed())));
    }

    @Test
    public void fab_callASnackbarByClickingOnFABButton() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.snackbar_text)).check(matches(withText(endsWith(SNACKBAR))));
    }

    @Test
    public void fab_dismissDisplayedCommunique() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.snackbar_text))
                .perform(swipeRight())
                .check(matches(not(isDisplayed())));
    }
}