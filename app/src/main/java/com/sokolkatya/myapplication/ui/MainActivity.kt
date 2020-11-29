package com.sokolkatya.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sokolkatya.myapplication.R

class MainActivity : AppCompatActivity() {

    private var movieListFragment: Fragment? = null
    private var movieDetailsFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (movieListFragment == null) {
            movieListFragment = FragmentMoviesList()
            if (supportFragmentManager.findFragmentByTag("movieListFragment") == null) {
                supportFragmentManager.beginTransaction()
                        .apply {
                            add(R.id.flContainer, movieListFragment!!, "movieListFragment")
                            commit()
                        }
            }
        }
    }
}