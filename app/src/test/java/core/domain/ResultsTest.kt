package core.domain

import org.junit.After
import org.junit.Assert
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
        Assert.assertEquals("display", apiResults.results[0].display_title)
    }

    @Test
    fun getMpaa_rating() {
        Assert.assertEquals("1", apiResults.results[0].mpaa_rating)
    }

    @Test
    fun getCritics_pick() {
        Assert.assertEquals(1, apiResults.results[0].critics_pick)
    }

    @Test
    fun getByline() {
        Assert.assertEquals("byline", apiResults.results[0].byline)
    }

    @Test
    fun getHeadline() {
        Assert.assertEquals("headline", apiResults.results[0].headline)
    }

    @Test
    fun getSummary_short() {
        Assert.assertEquals("summary", apiResults.results[0].summary_short)
    }

    @Test
    fun getPublication_date() {
        Assert.assertEquals("publication", apiResults.results[0].publication_date)
    }

    @Test
    fun getOpening_date() {
        Assert.assertEquals("opening", apiResults.results[0].opening_date)
    }

    @Test
    fun getDate_updated() {
        Assert.assertEquals("updated", apiResults.results[0].date_updated)
    }

    @Test
    fun getLink() {
        Assert.assertEquals("type", apiResults.results[0].link.type)
        Assert.assertEquals("link", apiResults.results[0].link.suggested_link_text)
        Assert.assertEquals("url", apiResults.results[0].link.url)
    }

    @Test
    fun getMultimedia() {
        Assert.assertEquals(1, apiResults.results[0].multimedia.height)
        Assert.assertEquals(1, apiResults.results[0].multimedia.width)
        Assert.assertEquals("src", apiResults.results[0].multimedia.src)
        Assert.assertEquals("type", apiResults.results[0].multimedia.type)
    }

}
