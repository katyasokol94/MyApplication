package com.sokolkatya.myapplication.data.mapper

import com.sokolkatya.myapplication.data.entities.api.Department
import com.sokolkatya.myapplication.data.entities.api.Genre
import com.sokolkatya.myapplication.data.entities.api.MovieDetails
import com.sokolkatya.myapplication.data.entities.api.Result
import com.sokolkatya.myapplication.data.entities.database.DBGenre
import com.sokolkatya.myapplication.data.entities.database.DBMovieItem
import com.sokolkatya.myapplication.data.entities.database.Movie
import com.sokolkatya.myapplication.data.entities.database.MovieGenre
import com.sokolkatya.myapplication.ui.entities.Actor
import com.sokolkatya.myapplication.ui.entities.MovieDetailsItem
import com.sokolkatya.myapplication.ui.entities.MovieItem

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

fun transformMovies(
    results: List<Result>,
    imageUrl: String
): MutableList<Movie> {
    return mutableListOf<Movie>().apply {
        results.forEach {
            add(
                Movie(
                    movieId = it.id.toInt(),
                    title = it.title,
                    posterPath = imageUrl.plus(it.posterPath),
                    voteAverage = it.voteAverage.toFloat(),
                    voteCount = it.voteCount.toInt(),
                    minimumAge = if (it.adult) 16 else 13
                )
            )
        }
    }
}

fun transformGenres(
    results: List<Result>
): MutableList<MovieGenre> {
    val movieGenre = mutableListOf<MovieGenre>()
    results.forEach { result ->
        result.genreIDS.forEach {
            movieGenre.add(MovieGenre(movieId = result.id.toInt(), genreId = it))
        }
    }
    return movieGenre
}

fun transformMovies(
    dbMovieItems: List<DBMovieItem>,
    dbGenre: List<DBGenre>
): MutableList<MovieItem> {
    return mutableListOf<MovieItem>().apply {
        dbMovieItems.forEach { dbMovieItem ->
            add(
                MovieItem(
                    dbMovieItem.id,
                    dbMovieItem.title,
                    dbMovieItem.poster,
                    dbMovieItem.ratings,
                    dbMovieItem.numberOfRatings,
                    dbMovieItem.minimumAge,
                    dbGenre
                            .filter { it.movieId == dbMovieItem.id }
                            .map { Genre(it.genreId, it.name) }
                )
            )
        }
    }
}
