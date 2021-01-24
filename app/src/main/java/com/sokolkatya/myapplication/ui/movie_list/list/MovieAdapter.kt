package com.sokolkatya.myapplication.ui.movie_list.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sokolkatya.myapplication.R
import com.sokolkatya.myapplication.ui.entities.MovieItem

class MovieAdapter(
    private var listener: OnMovieClickListener
) : ListAdapter<MovieItem, MovieViewHolder>(DiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
            MovieViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_holder_movie, parent, false),
                listener
            )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(actors: List<MovieItem>) {
        submitList(actors)
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<MovieItem>() {

        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem == newItem
    }

    interface OnMovieClickListener {

        fun onClick(movie: MovieItem)
    }
}