package com.movie.application.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.application.view.HomeActivity
import com.movie.application.view.adapter.MovieItemAdapter
import com.movie.application.view.adapter.OnMovieClickListener
import com.movie.application.viewModel.MovieListViewModel
import com.movie.application.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.movie_list_fragment.*
import org.codejudge.application.R

/**
 * Fragment to show list of movie
 */
class MovieListFragment : Fragment() {
    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter: MovieItemAdapter
    private lateinit var fragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!this::fragmentView.isInitialized)
            fragmentView = inflater.inflate(R.layout.activity_main, container, false)
        return fragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        setupViewModel()
        setupObservers()
        if (viewModel.movieList.value != null)
            return
        if (viewModel.isNetworkAvailable(requireContext())) {
            viewModel.fetchMovie()
        } else {
            Toast.makeText(requireContext(), getString(R.string.no_internet), Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Set up viewModels
     */
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                requireActivity(),
                ViewModelFactory()
        ).get(MovieListViewModel::class.java)
    }

    /**
     * Set up observers and listen for API result
     */
    private fun setupObservers() {
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter.setDataSource(it)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            loading_bar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    /**
     * Set up UI
     */
    private fun setupUI() {
        adapter = MovieItemAdapter(mutableListOf(), requireContext())
        var clickListener = object : OnMovieClickListener {
            override fun onMovieClick(position: Int) {
                viewModel.selectedMovieIndex.postValue(position)
                (activity as HomeActivity?)!!.moveToDetailsFragment(position)
            }
        }
        adapter.setClickListener(clickListener)
        movie_list.setHasFixedSize(true)
        movie_list.layoutManager = LinearLayoutManager(requireContext())
        movie_list.adapter = adapter
    }
}
