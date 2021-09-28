package com.movie.application.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.movie.application.R
import com.movie.application.utilities.EspressoIdlingResource
import com.movie.application.viewModel.MovieListViewModel
import com.movie.application.viewModel.ViewModelFactory
import core.domain.Results
import kotlinx.android.synthetic.main.fragment_scrolling.*

class MovieDetailsFragment : Fragment() {
    private lateinit var viewModel: MovieListViewModel
    private var selectedIndex = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scrolling, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        selectedIndex = arguments?.getInt("selectedIndex")!!
        showData(viewModel.movieList?.value?.get(selectedIndex))
    }

    private fun showData(movie: Results?) {
        movie?.let {
            Glide.with(requireContext())
                    .load(it.multimedia?.src)
                    .into(ivMovieImage)
            tvDetailsMovieName.text = it.display_title
            tvDetailsMovieHeadLine.text = it.headline
            tvDetailsMovieSummary.text = it.summary_short
            tvDetailsPublication.text = it.publication_date
            tvDetailsOpen.text = it.opening_date
            tvDetailsUpdated.text = it.date_updated
        }
        EspressoIdlingResource.decrement()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                requireActivity(),
                ViewModelFactory()
        ).get(MovieListViewModel::class.java)
    }
}
