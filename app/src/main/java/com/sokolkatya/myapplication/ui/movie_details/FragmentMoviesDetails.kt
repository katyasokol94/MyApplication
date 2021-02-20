package com.sokolkatya.myapplication.ui.movie_details

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sokolkatya.myapplication.R
import com.sokolkatya.myapplication.extension.dipI
import com.sokolkatya.myapplication.extension.loadImage
import com.sokolkatya.myapplication.ui.entities.MovieDetailsItem
import com.sokolkatya.myapplication.ui.movie_details.list.ActorAdapter

class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {

    private lateinit var viewModel: MovieDetailsViewModel

    private var listener: MovieDetailsClickListener? = null

    private lateinit var tvBack: TextView
    private lateinit var tvName: TextView
    private lateinit var tvAgeRating: TextView
    private lateinit var tvGenres: TextView
    private lateinit var tvReviews: TextView
    private lateinit var tvStoryline: TextView
    private lateinit var tvCastTitle: TextView
    private lateinit var ivPicture: ImageView
    private lateinit var rbMovieRating: RatingBar
    private lateinit var rvActors: RecyclerView
    private lateinit var flProgress: FrameLayout

    private lateinit var adapter: ActorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = MovieDetailsViewModel(requireContext())
        viewModel.getMovieFromDb(movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findViews(view)
        setListeners()
        init()
    }

    private fun findViews(view: View) {
        tvBack = view.findViewById(R.id.tv_movie_back)
        rvActors = view.findViewById(R.id.rv_actors)
        ivPicture = view.findViewById(R.id.iv_movie_picture)
        tvName = view.findViewById(R.id.tv_movie_name)
        tvAgeRating = view.findViewById(R.id.tv_movie_age_rating)
        tvGenres = view.findViewById(R.id.tv_movie_genres)
        rbMovieRating = view.findViewById(R.id.rb_movie_rating)
        tvReviews = view.findViewById(R.id.tv_movie_review)
        tvStoryline = view.findViewById(R.id.tv_movie_storyline)
        tvCastTitle = view.findViewById(R.id.tv_movie_cast_title)
        flProgress = view.findViewById(R.id.fl_progress)
    }

    private fun setListeners() {
        tvBack.setOnClickListener {
            listener?.onBackClick()
        }
    }

    private fun init() {
        adapter = ActorAdapter()
        rvActors.apply {
            adapter = this@FragmentMoviesDetails.adapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {

                private val spaceWidth = context.dipI(8f)

                override fun getItemOffsets(
                    outRect: Rect,
                    itemPosition: Int,
                    parent: RecyclerView
                ) {
                    outRect.left = spaceWidth / 2
                    outRect.right = spaceWidth / 2
                }
            })
        }
        viewModel.movie.observe(this.viewLifecycleOwner, this::setData)
        viewModel.progress.observe(this.viewLifecycleOwner, this::setProgressVisibility)
        viewModel.getMovieDetails(movieId)
    }

    private fun setData(movie: MovieDetailsItem) {
        ivPicture.loadImage(url = movie.backdropPath)
        tvName.text = movie.title
        tvAgeRating.text = String.format(
            getString(R.string.movie_list_age_rating),
            movie.minimumAge
        )
        tvGenres.text = movie.genres.joinToString(separator = ", ") { it.name }
        rbMovieRating.rating = movie.voteAverage.toFloat().div(2)
        tvReviews.text = String.format(
            getString(R.string.movie_list_reviews),
            movie.voteCount
        )
        tvStoryline.text = movie.overview
        if (movie.actors.isEmpty()) {
            tvCastTitle.visibility = View.GONE
            rvActors.visibility = View.GONE
        } else {
            tvCastTitle.visibility = View.VISIBLE
            rvActors.visibility = View.VISIBLE
            adapter.setData(movie.actors)
        }
    }

    fun setProgressVisibility(show: Boolean) {
        flProgress.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()

        rvActors.adapter = null
    }

    fun setClickListener(l: MovieDetailsClickListener?) {
        listener = l
    }

    interface MovieDetailsClickListener {

        fun onBackClick()
    }

    private val movieId
        get() = arguments?.getInt(ARG_MOVIE_ID)
            ?: throw NullPointerException("MovieId can not be null!")

    companion object {

        private const val ARG_MOVIE_ID = "arg artworkId"

        fun newInstance(movieId: Int) =
                FragmentMoviesDetails().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_MOVIE_ID, movieId)
                    }
                }
    }
}