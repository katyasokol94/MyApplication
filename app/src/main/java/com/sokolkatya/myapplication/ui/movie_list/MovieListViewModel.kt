package com.sokolkatya.myapplication.ui.movie_list

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.sokolkatya.myapplication.data.entities.api.Genre
import com.sokolkatya.myapplication.data.entities.api.Result
import com.sokolkatya.myapplication.data.mapper.transformGenres
import com.sokolkatya.myapplication.data.mapper.transformMovies
import com.sokolkatya.myapplication.data.repository.MovieRepository
import com.sokolkatya.myapplication.data.repository.WorkRepository
import com.sokolkatya.myapplication.data.sourse.database.AppDatabase
import com.sokolkatya.myapplication.ui.entities.MovieItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel(
    appContext: Context
) : ViewModel() {

    private val movieRepository: MovieRepository = MovieRepository()
    private val appDAO = AppDatabase.getDatabase(appContext).appDAO()
    private val workRepository: WorkRepository = WorkRepository()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->

        Log.d("MovieListViewModel", "exceptionHandler: " + throwable.message)
    }

    private val moviesState: MutableLiveData<List<MovieItem>> = MutableLiveData(emptyList())
    val movies: LiveData<List<MovieItem>> get() = moviesState

    private val genres: MutableMap<Int, Genre> = mutableMapOf()
    private var imageUrl: String? = null

    init {
        WorkManager.getInstance(appContext)
                .enqueue(workRepository.loadDataRequest)
    }

    fun getMovies() {
        viewModelScope.launch(exceptionHandler) {
            coroutineScope {
                launch { loadConfig() }
                launch { loadGenre() }
            }
            var movies: MutableList<Result> = mutableListOf()
            coroutineScope {
                launch {
                    movies = movieRepository.loadPopular().results.toMutableList()
                }
            }

            coroutineScope {
                launch {
                    saveToDB(movies)
                }
            }

            getFromDB()
        }
    }

    private suspend fun loadGenre() = withContext(Dispatchers.IO) {
        if (genres.isEmpty()) {
            val genreList = movieRepository.loadGenre()
            genreList.genres.forEach { genres[it.id] = it }
            appDAO.insertGenres(
                genres.map {
                    com.sokolkatya.myapplication.data.entities.database.Genre(
                        it.key,
                        it.value.name
                    )
                }
            )
        }
    }

    private suspend fun loadConfig() = withContext(Dispatchers.IO) {
        if (imageUrl == null) {
            val config = movieRepository.loadConfig()
            imageUrl = config.images.baseURL.plus("original")
        }
    }

    private suspend fun saveToDB(movies: MutableList<Result>) = withContext(Dispatchers.Default) {
        appDAO.insertMovies(transformMovies(movies, imageUrl!!))
        appDAO.insertMovieGenres(transformGenres(movies.toList()))
    }

    private suspend fun getFromDB() = withContext(Dispatchers.Default) {
        moviesState.postValue(
            transformMovies(
                appDAO.getMovies(),
                appDAO.getMoviesGenres()
            )
        )
    }

    fun getMoviesFromDb() {
        viewModelScope.launch(exceptionHandler) {
            getFromDB()
        }
    }
}