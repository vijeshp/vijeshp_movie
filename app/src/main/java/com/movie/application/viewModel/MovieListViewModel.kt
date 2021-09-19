package com.movie.application.viewModel

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movie.application.repository.network.MovieDataSource
import core.data.MovieListRepository
import core.domain.Results
import core.interactors.FetchMovie
import core.interactors.Ineractors
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {

    var movieList = MutableLiveData<MutableList<Results>>()
    var isLoading = MutableLiveData<Boolean>()
    var selectedMovieIndex = MutableLiveData<Int>()

    /**
     * Fetch all movies from API
     */
    fun fetchMovie() {
        viewModelScope.launch {
            isLoading.postValue(true)
            var v = Ineractors(FetchMovie(MovieListRepository(MovieDataSource()))).fetchMovieList.invoke()
            isLoading.postValue(false)
            movieList.postValue(v.results as MutableList<Results>?)
        }
    }

    /**
     * Check if network is available
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo
                .isConnected
    }

}