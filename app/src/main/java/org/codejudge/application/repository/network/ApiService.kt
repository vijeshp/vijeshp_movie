package org.codejudge.application.repository.network

import org.codejudge.application.repository.data.ApiResults
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("reviews/all.json")
    suspend fun getMovieList(@Query("api-key")key:String): ApiResults
}