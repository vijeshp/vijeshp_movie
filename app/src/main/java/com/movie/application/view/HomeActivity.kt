package com.movie.application.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.movie.application.R

class HomeActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun moveToDetailsFragment(movieIndex: Int) {
        val bundle = bundleOf("selectedIndex" to movieIndex)
        navController?.navigate(R.id.action_movieListFragment_to_movieDetailsFragment, bundle)
    }
}
