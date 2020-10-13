package com.example.a31p_foodparcel

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FoodImageTest {
    private lateinit var nameToBeDisplayed: String;
            lateinit var emptyNameError: String;
            lateinit var emptyDateError: String;
            lateinit var invalidDateError: String;

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun initValidString() {
        // Specify a valid string.
        nameToBeDisplayed = "Braised Beef Stew"
        emptyNameError = "Name is required"
        emptyDateError = "Date is required"
        invalidDateError= "Valid date is required"

        //set up bundle to send to second activity
    }
    @Test
    fun saveEditNameSuccess() {
        //enter new image name : Braised Beef Stew(hot)
        //successfully save and go back to Main activity
        Intents.init()
        onView(withId(R.id.image_01)).perform(click())
        intended(hasComponent(ImageDisplayActivity::class.java.name))
        onView(withId(R.id.etName)).perform(typeText("(hot)"), closeSoftKeyboard(), pressBack())

        //backPress to MainActivity
        onView(withId(R.id.image_01)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.tvName01)).check(matches(withText("${nameToBeDisplayed}(hot)")))
        //onView(withId(R.id.etName)).check(matches(hasNoErrorText)));
        Intents.release()
    }
    @Test
    fun testEmptyImageName() {
        Intents.init()
        onView(withId(R.id.image_01)).perform(click())
        intended(hasComponent(ImageDisplayActivity::class.java.name))
        //type empty string for name
        onView(withId(R.id.etName)).perform(replaceText(""), closeSoftKeyboard(), pressBack())

        //backPress not allow to MainActivity
        onView(withId(R.id.etName)).check(matches(hasErrorText(emptyNameError)))
        Intents.release()
    }
    @Test
    fun testInvalidDate() {
        Intents.init()
        onView(withId(R.id.image_03)).perform(click())
        intended(hasComponent(ImageDisplayActivity::class.java.name))
        //type invalid date : 12/06
        onView(withId(R.id.etDate)).perform(replaceText("1206"), closeSoftKeyboard(), pressBack())

        //backPress not allow to MainActivity
        onView(withId(R.id.etDate)).check(matches(hasErrorText(invalidDateError)))
        Intents.release()
    }
    @Test
    fun testEmptyDate() {
        Intents.init()
        onView(withId(R.id.image_02)).perform(click())
        intended(hasComponent(ImageDisplayActivity::class.java.name))
        //type empty string for date
        onView(withId(R.id.etDate)).perform(replaceText(""), closeSoftKeyboard(), pressBack())

        //backPress not allow to MainActivity
        onView(withId(R.id.etDate)).check(matches(hasErrorText(emptyDateError)))

        Intents.release()
    }
    @Test
    fun saveDateSuccess() {
        Intents.init()
        onView(withId(R.id.image_02)).perform(click())
        intended(hasComponent(ImageDisplayActivity::class.java.name))
        onView(withId(R.id.etDate)).perform(replaceText("12122020"), closeSoftKeyboard() , pressBack())

        //backPress to MainActivity
        onView(withId(R.id.image_02)).perform(click())
        onView(withId(R.id.imageView)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.etDate)).check(matches(withText("12/12/2020")))
        Intents.release()
    }
    @Test
    fun testImageClickToLaunchSecondActivity() {
        Intents.init()
        onView(withId(R.id.image_01)).perform(click())
        onView(withId(R.id.etName)).check(matches(withText(nameToBeDisplayed)))
        Intents.release()
    }


}
