package org.codejudge.application.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.codejudge.application.repository.MovieRepository
import org.codejudge.application.repository.network.ApiHelper

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(MovieRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}