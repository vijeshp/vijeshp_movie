package com.movie.application.repository.network

import core.domain.Constants

/**
 * API Helper class for fetching movie list
 */
class ApiHelper(private val apiService: ApiService) {
    suspend fun getMovieListHelper() = apiService.getMovieList(Constants.API_KEY)
}
