package com.movie.application.view.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner

import com.movie.application.R
import com.movie.application.utilities.EspressoIdlingResource
import com.movie.application.view.HomeActivity
import com.movie.application.view.adapter.MovieItemAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentDetailedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun before() {
        IdlingPolicies.setIdlingResourceTimeout(3, TimeUnit.MINUTES);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
     }

    @After
    fun after() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun check_if_recyclerview_is_loading() {
        onView(withId(R.id.movie_list)).check(matches(isDisplayed()))
    }

    @Test
    fun check_if_screen_navigating_to_details() {
        onView(withId(R.id.movie_list))
            .perform(actionOnItemAtPosition<MovieItemAdapter.ViewHolder>(0, click()));
        onView(withId(R.id.tvDetailsMovieName)).check(matches(isDisplayed()))
    }

    @Test
    fun check_if_backpress_from_details() {
        onView(withId(R.id.movie_list))
            .perform(actionOnItemAtPosition<MovieItemAdapter.ViewHolder>(0, click()));
        onView(withId(R.id.tvDetailsMovieName)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.movie_list)).check(matches(isDisplayed()))
    }

}