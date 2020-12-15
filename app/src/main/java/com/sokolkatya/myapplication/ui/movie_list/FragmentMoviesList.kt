package com.sokolkatya.myapplication.ui.movie_list

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sokolkatya.myapplication.R
import com.sokolkatya.myapplication.extension.dipI
import com.sokolkatya.myapplication.ui.entities.Movie

class FragmentMoviesList : Fragment(), MovieAdapter.OnMovieClickListener {

    private lateinit var rvMovies: RecyclerView

    private var listener: MovieClickListener? = null
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findViews(view)
        init()
    }

    private fun findViews(view: View) {
        rvMovies = view.findViewById(R.id.rv_movies)
    }

    private fun init() {
        adapter = MovieAdapter(this)
        rvMovies.apply {
            adapter = this@FragmentMoviesList.adapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {

                private val spacingVertical: Int = context.dipI(11f)
                private val spacingHorizontal: Int = context.dipI(8f)
                private val spanCount: Int = 2

                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams

                    val spanIndex = layoutParams.spanIndex
                    val position = parent.getChildAdapterPosition(view)
                    if (position < spanCount) {
                        outRect.top = spacingVertical
                    }
                    outRect.bottom = spacingVertical
                    outRect.right = if (spanIndex == 0) spacingHorizontal else 0
                    outRect.left = if (spanIndex == 0) 0 else spacingHorizontal
                }
            })
        }
        adapter.setData(composeMoviesList())
    }

    private fun composeMoviesList(): MutableList<Movie> =
            mutableListOf<Movie>()
                    .apply {
                        add(
                            Movie(
                                R.drawable.movie_item_1,
                                R.string.movie_list_avengers_end_game,
                                4,
                                13,
                                R.string.movie_list_action_adventure_fantasy,
                                125,
                                137,
                                false
                            )
                        )
                        add(
                            Movie(
                                R.drawable.movie_item_1,
                                R.string.movie_list_avengers_end_game,
                                1,
                                13,
                                R.string.movie_list_action_adventure_fantasy,
                                125,
                                137,
                                true
                            )
                        )
                        add(
                            Movie(
                                R.drawable.movie_item_1,
                                R.string.movie_list_avengers_end_game,
                                5,
                                13,
                                R.string.movie_list_action_adventure_fantasy,
                                125,
                                137,
                                true
                            )
                        )
                        add(
                            Movie(
                                R.drawable.movie_item_1,
                                R.string.movie_list_avengers_end_game,
                                2,
                                13,
                                R.string.movie_list_action_adventure_fantasy,
                                125,
                                137,
                                false
                            )
                        )
                    }

    fun setClickListener(l: MovieClickListener?) {
        listener = l
    }

    interface MovieClickListener {

        fun onClick()
    }

    override fun onClick(movie: Movie) {
        listener?.onClick()
    }
}