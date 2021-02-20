package com.sokolkatya.myapplication.data.mapper

import com.sokolkatya.myapplication.data.entities.api.Department
import com.sokolkatya.myapplication.data.entities.api.Genre
import com.sokolkatya.myapplication.data.entities.api.MovieDetails
import com.sokolkatya.myapplication.data.entities.api.Result
import com.sokolkatya.myapplication.data.entities.database.DBGenre
import com.sokolkatya.myapplication.data.entities.database.DBMovieDetailsItem
import com.sokolkatya.myapplication.data.entities.database.DBMovieItem
import com.sokolkatya.myapplication.data.entities.database.Movie
import com.sokolkatya.myapplication.data.entities.database.MovieActor
import com.sokolkatya.myapplication.data.entities.database.MovieGenre
import com.sokolkatya.myapplication.ui.entities.Actor
import com.sokolkatya.myapplication.ui.entities.MovieDetailsItem
import com.sokolkatya.myapplication.ui.entities.MovieItem

fun transformMovieDetails(
    movieDetails: DBMovieDetailsItem,
    genres: List<com.sokolkatya.myapplication.data.entities.database.Genre>,
    actors: List<com.sokolkatya.myapplication.data.entities.database.Actor>
): MovieDetailsItem =
        MovieDetailsItem(
            movieDetails.minimumAge,
            movieDetails.backdropPath,
            mutableListOf<Genre>().apply {
                genres.forEach {
                    add(Genre(it.genreId, it.name))
                }
            },
            movieDetails.id.toLong(),
            movieDetails.overview,
            movieDetails.runtime.toLong(),
            movieDetails.title,
            movieDetails.voteAverage.toDouble(),
            movieDetails.voteCount.toLong(),
            mutableListOf<Actor>().apply {
                actors.forEach { actor ->
                    add(Actor(actor.actorId, actor.name, actor.picture))
                }
            }
        )

fun transformActors(
    result: MovieDetails,
    imageUrl: String
): MutableList<com.sokolkatya.myapplication.data.entities.database.Actor> =
        mutableListOf<com.sokolkatya.myapplication.data.entities.database.Actor>().apply {
            result.credits.cast
                    .filter { it.knownForDepartment == Department.ACTING && it.profilePath != null }
                    .forEach { cast ->
                        add(
                            com.sokolkatya.myapplication.data.entities.database.Actor(
                                cast.id.toInt(),
                                cast.name,
                                imageUrl.plus(cast.profilePath)
                            )
                        )
                    }
        }

fun transformMovieActors(
    actors: List<com.sokolkatya.myapplication.data.entities.database.Actor>,
    movieId: Int
): MutableList<MovieActor> =
        mutableListOf<MovieActor>().apply {
            actors.forEach {
                add(MovieActor(movieId, it.actorId))
            }
        }

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
