package org.codejudge.application.repository.network

import org.codejudge.application.helper.Constants

class ApiHelper(private val apiService: ApiService) {
    suspend fun getMovieListHelper() = apiService.getMovieList(Constants.API_KEY)
}