package org.codejudge.application.model

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.codejudge.application.repository.MovieRepository
import org.codejudge.application.repository.data.Results

class MainActivityViewModel (private val mainRepository: MovieRepository) : ViewModel() {

    var movieList = MutableLiveData<MutableList<Results>>()
    var isLoading = MutableLiveData<Boolean>()
    var selectedMovieIndex= MutableLiveData<Int>()


    fun setSelectedItem(item: Int) {
        selectedMovieIndex.value = item
    }

    /**
     * Fetch all restaurants from API
     */
    fun fetchMovie(){
        viewModelScope.launch {
            isLoading.postValue(true)
            var v = mainRepository.getMovieList()
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