package com.sokolkatya.myapplication.data.mapper

import com.sokolkatya.myapplication.data.entities.Genre
import com.sokolkatya.myapplication.data.entities.Result
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