package org.codejudge.application.view.fragments

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_scrolling.*
import org.codejudge.application.R
import org.codejudge.application.model.MainActivityViewModel
import org.codejudge.application.model.ViewModelFactory
import org.codejudge.application.repository.data.Results
import org.codejudge.application.repository.network.ApiHelper
import org.codejudge.application.repository.network.RetrofitBuilder
import org.codejudge.application.view.adapter.MovieItemAdapter

class MovieDetailsFragment : Fragment() {
    private lateinit var viewModel: MainActivityViewModel
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

    private fun showData(movie:Results?) {
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
    }



    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            requireActivity(),
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainActivityViewModel::class.java)
    }
}