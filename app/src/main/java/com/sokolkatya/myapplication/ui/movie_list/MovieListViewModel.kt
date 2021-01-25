package com.sokolkatya.myapplication.ui.movie_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokolkatya.myapplication.data.entities.Genre
import com.sokolkatya.myapplication.data.mapper.transformMovies
import com.sokolkatya.myapplication.data.repository.MovieRepository
import com.sokolkatya.myapplication.ui.entities.MovieItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel() : ViewModel() {

    private val movieRepository: MovieRepository = MovieRepository()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->

        Log.d("MovieListViewModel", "exceptionHandler: " + throwable.message)
    }

    private val moviesState: MutableLiveData<List<MovieItem>> = MutableLiveData(emptyList())
    val movies: LiveData<List<MovieItem>> get() = moviesState

    private val genres: MutableMap<Int, Genre> = mutableMapOf()
    private var imageUrl: String? = null

    fun getMovies() {
        viewModelScope.launch(exceptionHandler) {
            coroutineScope {
                launch { loadConfig() }
                launch { loadGenre() }
            }
            val movies = movieRepository.loadPopular()
            moviesState.value = transformMovies(movies.results, genres, imageUrl!!)
        }
    }

    private suspend fun loadGenre() = withContext(Dispatchers.IO) {
        if (genres.isEmpty()) {
            val genreList = movieRepository.loadGenre()
            genreList.genres.forEach { genres[it.id] = it }
        }
    }

    private suspend fun loadConfig() = withContext(Dispatchers.IO) {
        if (imageUrl == null) {
            val config = movieRepository.loadConfig()
            imageUrl = config.images.baseURL.plus("original")
        }
    }
}