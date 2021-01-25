package com.sokolkatya.myapplication.ui.movie_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokolkatya.myapplication.data.mapper.transformMovieDetails
import com.sokolkatya.myapplication.data.repository.MovieRepository
import com.sokolkatya.myapplication.ui.entities.MovieDetailsItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel() : ViewModel() {

    private val movieRepository: MovieRepository = MovieRepository()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("MovieListViewModel", "exceptionHandler: " + throwable.message)
    }

    private var imageUrl: String? = null

    private val movieState: MutableLiveData<MovieDetailsItem> = MutableLiveData()
    val movie: LiveData<MovieDetailsItem> get() = movieState

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            coroutineScope {
                launch { loadConfig() }
            }
            val movieDetail = movieRepository.loadMovieDetails(movieId)
            movieState.value = transformMovieDetails(movieDetail, imageUrl!!)
        }
    }

    private suspend fun loadConfig() = withContext(Dispatchers.IO) {
        if (imageUrl == null) {
            val config = movieRepository.loadConfig()
            imageUrl = config.images.baseURL.plus("original")
        }
    }
}