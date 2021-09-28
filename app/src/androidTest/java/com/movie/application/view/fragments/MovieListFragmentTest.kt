package com.movie.application.view.fragments


import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.runner.AndroidJUnit4
import com.movie.application.R
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieListFragmentTest {

    private lateinit var scenario: FragmentScenario<MovieListFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.AppTheme)
    }
    @Test
    fun `check_if_progressbar_is_loading`() {
        onView(withId(R.id.loading_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.loading_bar)).check(matches((isEnabled())))
    }
}