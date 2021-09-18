package org.codejudge.application.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_list_fragment.*
import org.codejudge.application.R
import org.codejudge.application.model.MainActivityViewModel
import org.codejudge.application.model.ViewModelFactory
import org.codejudge.application.repository.network.ApiHelper
import org.codejudge.application.repository.network.RetrofitBuilder
import org.codejudge.application.view.HomeActivity
import org.codejudge.application.view.adapter.MovieItemAdapter
import org.codejudge.application.view.adapter.OnMovieClickListener

class MovieListFragment : Fragment() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: MovieItemAdapter
    private lateinit var fragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(!this::fragmentView.isInitialized)
            fragmentView  = inflater.inflate(R.layout.activity_main, container, false)
        return fragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUI()
        setupViewModel()
        setupObservers()
        if(viewModel.movieList.value !=null)
            return
        if (viewModel.isNetworkAvailable(requireContext())) {
            viewModel.fetchMovie()
        } else {
            Toast.makeText(requireContext(), getString(R.string.no_internet), Toast.LENGTH_LONG).show()
        }
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            requireActivity(),
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainActivityViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            adapter.setDataSource(it)
        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            loading_bar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

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