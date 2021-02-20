package com.sokolkatya.myapplication.ui.movie_details

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokolkatya.myapplication.data.entities.api.MovieDetails
import com.sokolkatya.myapplication.data.mapper.transformActors
import com.sokolkatya.myapplication.data.mapper.transformMovieActors
import com.sokolkatya.myapplication.data.mapper.transformMovieDetails
import com.sokolkatya.myapplication.data.repository.MovieRepository
import com.sokolkatya.myapplication.data.sourse.database.AppDatabase
import com.sokolkatya.myapplication.ui.entities.MovieDetailsItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(
    appContext: Context
) : ViewModel() {

    private val movieRepository: MovieRepository = MovieRepository()
    private val appDAO = AppDatabase.getDatabase(appContext).appDAO()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("MovieListViewModel", "exceptionHandler: " + throwable.message)
    }

    private var imageUrl: String? = null

    private val movieState: MutableLiveData<MovieDetailsItem> = MutableLiveData()
    val movie: LiveData<MovieDetailsItem> get() = movieState

    private val progressState: MutableLiveData<Boolean> = MutableLiveData()
    val progress: LiveData<Boolean> get() = progressState

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(exceptionHandler) {
            progressState.value = true
            coroutineScope {
                launch { loadConfig() }
            }
            val movieDetail = movieRepository.loadMovieDetails(movieId)
            coroutineScope {
                launch {
                    saveToDB(movieDetail)
                }
            }

            getFromDB(movieId)
            progressState.value = false
        }
    }

    private suspend fun loadConfig() = withContext(Dispatchers.IO) {
        if (imageUrl == null) {
            val config = movieRepository.loadConfig()
            imageUrl = config.images.baseURL.plus("original")
        }
    }

    private suspend fun saveToDB(movie: MovieDetails) = withContext(Dispatchers.Default) {
        appDAO.updateMovieDetails(
            movie.id.toInt(),
            imageUrl.plus(movie.backdropPath),
            movie.overview,
            movie.runtime.toInt()
        )
        val actors = transformActors(movie, imageUrl!!)
        appDAO.insertActors(actors)
        appDAO.insertMovieActors(transformMovieActors(actors, movie.id.toInt()))
    }

    fun getMovieFromDb(movieId: Int) {
        viewModelScope.launch(exceptionHandler) {
            getFromDB(movieId)
        }
    }

    private suspend fun getFromDB(movieId: Int) = withContext(Dispatchers.Default) {
        movieState.postValue(
            transformMovieDetails(
                appDAO.getMovieDetails(movieId),
                appDAO.getMovieGenres(movieId),
                appDAO.getMovieActors(movieId)
            )
        )
    }
}