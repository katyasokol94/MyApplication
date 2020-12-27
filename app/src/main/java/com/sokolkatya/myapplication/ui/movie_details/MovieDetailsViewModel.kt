package com.sokolkatya.myapplication.ui.movie_details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokolkatya.myapplication.data.Movie
import com.sokolkatya.myapplication.data.loadMovies
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val context: Context
) : ViewModel() {

    private val moviesState: MutableLiveData<Movie> = MutableLiveData()
    val movies: LiveData<Movie> get() = moviesState

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            val movies = loadMovies(context)
            moviesState.value = movies.find { it.id == movieId }
        }
    }
}