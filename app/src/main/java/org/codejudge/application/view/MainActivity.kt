package org.codejudge.application.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.codejudge.application.R
import org.codejudge.application.model.MainActivityViewModel
import org.codejudge.application.model.ViewModelFactory
import org.codejudge.application.repository.network.ApiHelper
import org.codejudge.application.repository.network.RetrofitBuilder
import org.codejudge.application.view.adapter.MovieItemAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var movieList: RecyclerView
    private lateinit var loadingBar: ProgressBar
    private lateinit var adapter: MovieItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
        if (viewModel.isNetworkAvailable(applicationContext)) {
            viewModel.fetchMovie()
        } else {
            Toast.makeText(applicationContext, getString(R.string.no_internet), Toast.LENGTH_LONG).show()
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainActivityViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.movieList.observe(this, Observer {
            adapter.setDataSource(it)
        })
        viewModel.isLoading.observe(this, Observer {
            loadingBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun setupUI() {
        loadingBar = findViewById(R.id.loading_bar)
        movieList = findViewById(R.id.movie_list)
        adapter = MovieItemAdapter(mutableListOf(), this)
        movieList.setHasFixedSize(true)
        movieList.layoutManager = LinearLayoutManager(this)
        movieList.adapter = adapter
    }
}
