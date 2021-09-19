package com.movie.application.repository.network

import core.domain.ApiResults
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API service to fetch movie list
 */
interface ApiService {
    @GET("reviews/all.json")
    suspend fun getMovieList(@Query("api-key") key: String): ApiResults
}
