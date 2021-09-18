package org.codejudge.application.repository

import org.codejudge.application.repository.network.ApiHelper

class MovieRepository(private val apiHelper: ApiHelper) {
    suspend fun getMovieList() = apiHelper.getMovieListHelper()
}