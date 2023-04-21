package com.example.databinding_application

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun onActivityLaunch_showAllUiCorrectly() {
        onView(withText("00:00:00")).check(matches(isDisplayed()))
        onView(withId(R.id.startButton)).check(matches(isDisplayed()))

        onView(withId(R.id.resetButton)).check(matches(isDisplayed()))
        onView(withId(R.id.stopwatch)).check(matches(isDisplayed()))
    }

    @Test
    fun onStartClicked_showTimerCorrectly() {
        onView(withText("00:00:00")).check(matches(isDisplayed()))
        onView(withId(R.id.startButton)).perform(click())
        onView(isRoot()).perform(waitFor(3000))

        onView(withId(R.id.stopwatch)).check(matches(withText("00:00:03")))
    }

    @Test
    fun onStopClicked_stopsTheTimer() {
        onView(withText("00:00:00")).check(matches(isDisplayed()))
        onView(withId(R.id.startButton)).perform(click())
        onView(isRoot()).perform(waitFor(3000))

        onView(withId(R.id.stopButton)).perform(click())
        onView(isRoot()).perform(waitFor(3000))
        onView(withId(R.id.stopwatch)).check(matches(withText("00:00:03")))
    }

    @Test
    fun onResetClicked_resetsTheTimer() {
        onView(withText("00:00:00")).check(matches(isDisplayed()))
        onView(withId(R.id.startButton)).perform(click())
        onView(isRoot()).perform(waitFor(3000))

        onView(withId(R.id.resetButton)).perform(click())
        onView(withId(R.id.stopwatch)).check(matches(withText("00:00:00")))
    }


    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}