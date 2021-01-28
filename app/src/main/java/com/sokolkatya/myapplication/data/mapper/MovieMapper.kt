package com.sokolkatya.myapplication.data.mapper

import com.sokolkatya.myapplication.data.entities.api.Department
import com.sokolkatya.myapplication.data.entities.api.Genre
import com.sokolkatya.myapplication.data.entities.api.MovieDetails
import com.sokolkatya.myapplication.data.entities.api.Result
import com.sokolkatya.myapplication.ui.entities.Actor
import com.sokolkatya.myapplication.ui.entities.MovieDetailsItem
import com.sokolkatya.myapplication.ui.entities.MovieItem

fun transformMovies(
    results: List<Result>,
    genres: MutableMap<Int, Genre>,
    imageUrl: String
): MutableList<MovieItem> {
    return mutableListOf<MovieItem>().apply {
        results.forEach {
            add(
                MovieItem(
                    it.id.toInt(),
                    it.title,
                    imageUrl.plus(it.posterPath),
                    it.voteAverage.toFloat(),
                    it.voteCount.toInt(),
                    if (it.adult) 16 else 13,
                    mutableListOf<Genre>().apply {
                        it.genreIDS.forEach { genreId ->
                            if (genres.containsKey(genreId))
                                add(genres[genreId]!!)
                        }
                    }
                )
            )
        }
    }
}

fun transformMovieDetails(
    result: MovieDetails,
    imageUrl: String
): MovieDetailsItem =
        MovieDetailsItem(
            if (result.adult) 16 else 13,
            imageUrl.plus(result.backdropPath),
            result.genres,
            result.id,
            result.overview,
            result.runtime,
            result.title,
            result.voteAverage,
            result.voteCount,
            mutableListOf<Actor>().apply {
                result.credits.cast
                        .filter { it.knownForDepartment == Department.ACTING && it.profilePath != null }
                        .forEach { cast ->
                            add(Actor(cast.id.toInt(), cast.name, imageUrl.plus(cast.profilePath)))
                        }
            }
        )