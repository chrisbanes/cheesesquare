package com.support.android.designlibdemo.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.support.android.designlibdemo.Cheeses;
import com.support.android.designlibdemo.MainActivity;
import com.support.android.designlibdemo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

/**
 * @author piotr on 16.11.15.
 */
@RunWith(AndroidJUnit4.class)
public class ViewPagerTest {

    @Rule
    public ActivityTestRule<MainActivity> mRule = new ActivityTestRule<>(MainActivity.class);
    String name = Cheeses.sCheeseStrings[getRandomPosition()];

    private int getRandomPosition() {
        Random rand = new Random();
        return rand.nextInt(Cheeses.sCheeseStrings.length);
    }

    @Test
    public void check_018_ifForecastItemDescriptionHasNoDefaultValue() throws InterruptedException {
        onData(anything()).inAdapterView(allOf(withId(R.id.recyclerview),
                hasSibling(withChild(withText(name))))).check(matches(isDisplayed()));
    }
}