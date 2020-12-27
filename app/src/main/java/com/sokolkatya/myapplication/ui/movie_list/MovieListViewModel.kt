package com.sokolkatya.myapplication.ui.movie_list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokolkatya.myapplication.data.Movie
import com.sokolkatya.myapplication.data.loadMovies
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val context: Context
) : ViewModel() {

    private val moviesState: MutableLiveData<List<Movie>> = MutableLiveData(emptyList())
    val movies: LiveData<List<Movie>> get() = moviesState

    fun getMovies() {
        viewModelScope.launch {
            val movies = loadMovies(context)
            moviesState.value = movies
        }
    }
}