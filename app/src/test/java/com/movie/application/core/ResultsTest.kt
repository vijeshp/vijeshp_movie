package com.movie.application.core

import core.domain.ApiResults
import core.domain.Link
import core.domain.Multimedia
import core.domain.Results
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResultsTest {
    private lateinit var apiResults: ApiResults
    @Before
    fun setUp() {
        apiResults = ApiResults("status", "copyright",
                true, 1, listOf(
                Results("display", "1",
                1, "byline", "headline", "summary",
                "publication", "opening", "updated",
                Link("type", "url", "link"), Multimedia("type", "src", 1, 1)
                )
            ))
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getDisplay_title() {
    }

    @Test
    fun getMpaa_rating() {
    }

    @Test
    fun getCritics_pick() {
    }

    @Test
    fun getByline() {
    }

    @Test
    fun getHeadline() {
    }

    @Test
    fun getSummary_short() {
    }

    @Test
    fun getPublication_date() {
    }

    @Test
    fun getOpening_date() {
    }

    @Test
    fun getDate_updated() {
    }

    @Test
    fun getLink() {
    }

    @Test
    fun getMultimedia() {
    }

    @Test
    operator fun component1() {
    }

    @Test
    operator fun component2() {
    }

    @Test
    operator fun component3() {
    }

    @Test
    operator fun component4() {
    }

    @Test
    operator fun component5() {
    }

    @Test
    operator fun component6() {
    }

    @Test
    operator fun component7() {
    }

    @Test
    operator fun component8() {
    }

    @Test
    operator fun component9() {
    }

    @Test
    operator fun component10() {
    }

    @Test
    operator fun component11() {
    }

    @Test
    fun copy() {
    }

    @Test
    fun testHashCode() {
    }

    @Test
    fun testEquals() {
    }
}
