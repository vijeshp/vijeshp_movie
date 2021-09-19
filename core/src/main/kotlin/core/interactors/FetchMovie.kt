package core.interactors

import core.data.MovieListRepository

class FetchMovie(private val movieListRepository: MovieListRepository) {
    suspend operator fun invoke() =
            movieListRepository.getMovies()
}
