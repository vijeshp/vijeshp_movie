package core.domain

import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ConstantsTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test constant`() {
        assertEquals("Jhi2R5v6NpGxARt2TPDnMqE55Bbt13A4",Constants.API_KEY)
    }
}