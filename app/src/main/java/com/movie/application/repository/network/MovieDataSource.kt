package com.movie.application.repository.network

import core.data.MovieListDataSource
import core.domain.ApiResults

class MovieDataSource : MovieListDataSource {
    override suspend fun getMovieList(): ApiResults {
        return ApiHelper(RetrofitBuilder.apiService).getMovieListHelper()
    }
}
