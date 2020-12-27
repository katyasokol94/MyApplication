package com.sokolkatya.myapplication.ui.movie_list.list

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sokolkatya.myapplication.R
import com.sokolkatya.myapplication.data.Movie
import com.sokolkatya.myapplication.extension.loadImage

class MovieViewHolder(
    private var itemView: View,
    private var listener: MovieAdapter.OnMovieClickListener
) : RecyclerView.ViewHolder(itemView) {

    private lateinit var data: Movie

    private var ivPhoto: ImageView = itemView.findViewById(R.id.iv_movie_item_picture)
    private var ivFavorite: ImageView = itemView.findViewById(R.id.iv_movie_item_favorite)
    private var tvAgeRating: TextView = itemView.findViewById(R.id.tv_movie_item_age_rating)
    private var tvGenres: TextView = itemView.findViewById(R.id.tv_movie_item_genres)
    private var rbRating: RatingBar = itemView.findViewById(R.id.rb_movie_item_rating)
    private var tvReview: TextView = itemView.findViewById(R.id.tv_movie_item_review)
    private var tvName: TextView = itemView.findViewById(R.id.tv_movie_item_name)
    private var tvDuration: TextView = itemView.findViewById(R.id.tv_movie_item_duration)

    init {
        itemView.setOnClickListener { listener.onClick(data) }
    }

    fun bind(movie: Movie) {
        data = movie
        ivPhoto.loadImage(movie.poster)
        tvAgeRating.text = String.format(
            itemView.context.getString(R.string.movie_list_age_rating),
            movie.minimumAge
        )
        tvGenres.text = movie.genres.joinToString(separator = ", ") { it.name }
        rbRating.rating = movie.ratings.div(2)
        tvReview.text = String.format(
            itemView.context.getString(R.string.movie_list_reviews),
            movie.numberOfRatings
        )
        tvName.text = movie.title
        tvDuration.text = String.format(
            itemView.context.getString(R.string.movie_list_duration),
            movie.runtime
        )
        ivFavorite.isSelected = movie.numberOfRatings.rem(2) > 0 // Just placeholder for setting 'Favorite' mark.
    }
}