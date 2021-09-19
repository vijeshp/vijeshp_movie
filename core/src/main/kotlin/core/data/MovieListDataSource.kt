package core.data

import core.domain.ApiResults

interface MovieListDataSource {
    suspend fun getMovieList(): ApiResults
}
