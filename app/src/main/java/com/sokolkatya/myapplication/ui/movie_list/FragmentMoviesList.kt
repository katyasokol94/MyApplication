package com.sokolkatya.myapplication.ui.movie_list

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sokolkatya.myapplication.R
import com.sokolkatya.myapplication.extension.dipI
import com.sokolkatya.myapplication.ui.entities.MovieItem
import com.sokolkatya.myapplication.ui.movie_list.list.MovieAdapter

class FragmentMoviesList : Fragment(R.layout.fragment_movies_list),
    MovieAdapter.OnMovieClickListener {

    private lateinit var viewModel: MovieListViewModel

    private lateinit var rvMovies: RecyclerView
    private lateinit var adapter: MovieAdapter

    private var listener: MovieClickListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findViews(view)
        init()
    }

    private fun findViews(view: View) {
        rvMovies = view.findViewById(R.id.rv_movies)
    }

    private fun init() {
        viewModel = MovieListViewModel(requireContext())
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
        viewModel.movies.observe(this.viewLifecycleOwner, this::updateAdapter)
        viewModel.getMovies()
    }

    private fun updateAdapter(movies: List<MovieItem>) {
        adapter.setData(movies)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        rvMovies.adapter = null
    }

    fun setClickListener(l: MovieClickListener?) {
        listener = l
    }

    override fun onClick(movie: MovieItem) {
        listener?.onClick(movie.id)
    }

    interface MovieClickListener {

        fun onClick(movieId: Int)
    }
}