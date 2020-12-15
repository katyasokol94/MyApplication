package com.sokolkatya.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.sokolkatya.myapplication.R
import com.sokolkatya.myapplication.ui.movie_details.FragmentMoviesDetails
import com.sokolkatya.myapplication.ui.movie_list.FragmentMoviesList

class MainActivity : AppCompatActivity(), FragmentMoviesList.MovieClickListener,
    FragmentMoviesDetails.MovieDetailsClickListener {

    private var movieListFragment =
            FragmentMoviesList().apply { setClickListener(this@MainActivity) }
    private var movieDetailsFragment =
            FragmentMoviesDetails().apply { setClickListener(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.popBackStack(
                BACK_STACK_ROOT_TAG,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )

            supportFragmentManager.beginTransaction()
                    .apply {
                        add(R.id.flContainer, movieListFragment)
                        addToBackStack(BACK_STACK_ROOT_TAG)
                        commit()
                    }
        }
    }

    override fun onClick() {
        supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.flContainer, movieDetailsFragment)
                    addToBackStack(null)
                    commit()
                }
    }

    override fun onBackClick() {
        removeLastFragment()
    }

    private fun removeLastFragment() {
        supportFragmentManager.popBackStackImmediate()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 1) {
            removeLastFragment()
        } else {
            super.onBackPressed()
        }
    }

    companion object {

        const val BACK_STACK_ROOT_TAG = "root_fragment"
    }
}