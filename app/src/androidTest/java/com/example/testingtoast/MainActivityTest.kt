package com.example.testingtoast

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.testingtoast.MainActivity.Companion.buildToastMessage
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
internal class MainActivityTest{

    @Test
    fun testShowDialogCaptureNameInput()
    {
        ActivityScenario.launch(MainActivity::class.java)
        val expectedName = "Mohamed"

        // Execute and Verify
        onView(withId(R.id.buttonLaunchDialog)).perform(click())
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        // Click OK button and check text enter name is displayed because the button not working
        onView(withText(R.string.text_ok)).perform(click())
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()))

        // Enter name
        onView(withId(com.afollestad.materialdialogs.input.R.id.md_input_message)).perform(typeText(expectedName))
        onView(withText(R.string.text_ok)).perform(click())

        // Check dialog dismiss
        onView(withText(R.string.text_enter_name)).check(doesNotExist())

        // Check the name is set to the TextView
        onView(withId(R.id.mainTextViewName)).check(matches(withText(expectedName)))

        onView(withId(R.id.mainTextViewName)).check(matches(withText(expectedName)))

        // Is toast displayed and is the message correct?
        onView(withText(buildToastMessage(expectedName))).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
}