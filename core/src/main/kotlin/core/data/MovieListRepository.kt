package core.data

class MovieListRepository(private val dataSource: MovieListDataSource) {
    suspend fun getMovies() = dataSource.getMovieList()
}
