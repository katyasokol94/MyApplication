package com.sokolkatya.myapplication.data.work_manager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.sokolkatya.myapplication.data.mapper.transformGenres
import com.sokolkatya.myapplication.data.mapper.transformMovies
import com.sokolkatya.myapplication.data.repository.MovieRepository
import com.sokolkatya.myapplication.data.sourse.database.AppDatabase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoadDataWork(context: Context, params: WorkerParameters) : Worker(context, params) {

    private val movieRepository: MovieRepository = MovieRepository()
    private val appDAO = AppDatabase.getDatabase(context).appDAO()
    private var coroutineScope = createScope()
    private var imageUrl: String? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->

        Log.d("MovieListViewModel", "exceptionHandler: " + throwable.message)
    }

    override fun doWork(): Result {

        coroutineScope.launch(exceptionHandler) {
            coroutineScope {
                launch { loadConfig() }
            }
            var movies: MutableList<com.sokolkatya.myapplication.data.entities.api.Result> =
                    mutableListOf()
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
        }
        coroutineScope.cancel("It's time")
        return Result.success()
    }

    private fun createScope() = CoroutineScope(Dispatchers.Default + Job())

    private suspend fun loadConfig() = withContext(Dispatchers.IO) {
        if (imageUrl == null) {
            val config = movieRepository.loadConfig()
            imageUrl = config.images.baseURL.plus("original")
        }
    }

    private suspend fun saveToDB(movies: MutableList<com.sokolkatya.myapplication.data.entities.api.Result>) =
            withContext(Dispatchers.Default) {
                appDAO.insertMovies(transformMovies(movies, imageUrl!!))
                appDAO.insertMovieGenres(transformGenres(movies.toList()))
            }
}