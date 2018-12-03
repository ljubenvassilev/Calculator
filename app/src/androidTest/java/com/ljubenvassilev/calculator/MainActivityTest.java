package com.ljubenvassilev.calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainActivityTest {

    @Before
    public void launchActivity() {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void testZero(){
        onView(withId(R.id.zero)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("0")));
    }

    @Test
    public void testOne(){
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("1")));
    }

    @Test
    public void testTwo(){
        onView(withId(R.id.two)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("2")));
    }

    @Test
    public void testThree(){
        onView(withId(R.id.three)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("3")));
    }

    @Test
    public void testFour(){
        onView(withId(R.id.four)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("4")));
    }

    @Test
    public void testFive(){
        onView(withId(R.id.five)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("5")));
    }

    @Test
    public void testSix(){
        onView(withId(R.id.six)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("6")));
    }

    @Test
    public void testSeven(){
        onView(withId(R.id.seven)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("7")));
    }

    @Test
    public void testEight(){
        onView(withId(R.id.eight)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("8")));
    }

    @Test
    public void testNine(){
        onView(withId(R.id.nine)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("9")));
    }

    @Test
    public void testDot(){
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.dot)).perform(click());
        onView(withId(R.id.five)).perform(click());
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.dot)).perform(click());
        onView(withId(R.id.three)).perform(click());
        onView(withId(R.id.equals)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("1.8")));
    }

    @Test
    public void testInput(){
        onView(withId(R.id.screen)).check(matches(withText("0")));
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.dot)).perform(click());
        onView(withId(R.id.five)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("1.5")));
    }

    @Test
    public void testMultiply(){
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.two)).perform(click());
        onView(withId(R.id.three)).perform(click());
        onView(withId(R.id.four)).perform(click());
        onView(withId(R.id.five)).perform(click());
        onView(withId(R.id.six)).perform(click());
        onView(withId(R.id.seven)).perform(click());
        onView(withId(R.id.nine)).perform(click());
        onView(withId(R.id.multiply)).perform(click());
        onView(withId(R.id.eight)).perform(click());
        onView(withId(R.id.equals)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("98765432")));
    }

    @Test
    public void testAdd(){
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.two)).perform(click());
        onView(withId(R.id.add)).perform(click());
        onView(withId(R.id.three)).perform(click());
        onView(withId(R.id.equals)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("15")));
    }

    @Test
    public void testDivide(){
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.two)).perform(click());
        onView(withId(R.id.divide)).perform(click());
        onView(withId(R.id.three)).perform(click());
        onView(withId(R.id.equals)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("4")));
    }

    @Test
    public void testSubtract(){
        onView(withId(R.id.one)).perform(click());
        onView(withId(R.id.two)).perform(click());
        onView(withId(R.id.subtract)).perform(click());
        onView(withId(R.id.three)).perform(click());
        onView(withId(R.id.equals)).perform(click());
        onView(withId(R.id.screen)).check(matches(withText("9")));
    }

    @Test
    public void testLongDelete(){
        onView(withId(R.id.five)).perform(click());
        onView(withId(R.id.five)).perform(click());
        onView(withId(R.id.five)).perform(click());
        onView(withId(R.id.delete)).perform(longClick());
        onView(withId(R.id.screen)).check(matches(withText("0")));
    }
}
