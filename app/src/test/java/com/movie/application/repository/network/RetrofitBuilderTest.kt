package com.movie.application.repository.network

import core.domain.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class RetrofitBuilderTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getApiService()  {
        runBlocking{
            var apiResult  = RetrofitBuilder.apiService.getMovieList(Constants.API_KEY)
            assertEquals("OK",apiResult.status)
        }
    }
}