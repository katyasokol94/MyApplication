package com.sokolkatya.myapplication.ui.movie_list

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sokolkatya.myapplication.R
import com.sokolkatya.myapplication.ui.entities.Movie

class MovieViewHolder(
    private var itemView: View,
    private var listener: MovieAdapter.OnMovieClickListener
) : RecyclerView.ViewHolder(itemView) {

    private lateinit var data: Movie

    private var ivPhoto: ImageView = itemView.findViewById(R.id.iv_movie_item_picture)
    private var ivFavorite: ImageView = itemView.findViewById(R.id.iv_movie_item_favorite)
    private var tvAgeRating: TextView = itemView.findViewById(R.id.tv_movie_item_age_rating)
    private var tvTags: TextView = itemView.findViewById(R.id.tv_movie_item_tag)
    private var rbRating: RatingBar = itemView.findViewById(R.id.rb_movie_item_rating)
    private var tvReview: TextView = itemView.findViewById(R.id.tv_movie_item_review)
    private var tvName: TextView = itemView.findViewById(R.id.tv_movie_item_name)
    private var tvDuration: TextView = itemView.findViewById(R.id.tv_movie_item_duration)

    init {
        itemView.setOnClickListener { listener.onClick(data) }
    }

    fun bind(movie: Movie) {
        data = movie
        ivPhoto.setImageResource(movie.photo)
        tvAgeRating.text = String.format(
            itemView.context.getString(R.string.movie_list_age_rating),
            movie.ageRating
        )
        tvTags.text = itemView.context.getString(movie.tags)
        rbRating.rating = movie.rating.toFloat()
        tvReview.text = String.format(
            itemView.context.getString(R.string.movie_list_reviews),
            movie.reviews
        )
        tvName.text = itemView.context.getString(movie.name)
        tvDuration.text = String.format(
            itemView.context.getString(R.string.movie_list_duration),
            movie.duration
        )
        ivFavorite.isSelected = movie.favorite
    }
}