package com.sokolkatya.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sokolkatya.myapplication.R

class MainActivity : AppCompatActivity(), FragmentMoviesList.MovieClickListener,
    FragmentMoviesDetails.MovieDetailsClickListener {

    private var movieListFragment =
            FragmentMoviesList().apply { setClickListener(this@MainActivity) }
    private var movieDetailsFragment =
            FragmentMoviesDetails().apply { setClickListener(this@MainActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.flContainer, movieListFragment)
                    commit()
                }
    }

    override fun onClick() {
        supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.flContainer, movieDetailsFragment)
                    commit()
                }
    }

    override fun onBackClick() {
        removeLastFragment()
    }

    private fun removeLastFragment() {
        if (supportFragmentManager.fragments.size > 1) {
            val lastFragment = supportFragmentManager.fragments.last()
            supportFragmentManager.beginTransaction()
                    .apply {
                        remove(lastFragment)
                        commit()
                    }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 1) {
            removeLastFragment()
        } else {
            super.onBackPressed()
        }
    }
}