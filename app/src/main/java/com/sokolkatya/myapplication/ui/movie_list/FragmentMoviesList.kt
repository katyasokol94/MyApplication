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
import com.sokolkatya.myapplication.data.Movie
import com.sokolkatya.myapplication.data.loadMovies
import com.sokolkatya.myapplication.extension.dipI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FragmentMoviesList : Fragment(), MovieAdapter.OnMovieClickListener {

    private val scope = CoroutineScope(Dispatchers.Default)

    private lateinit var rvMovies: RecyclerView
    private lateinit var adapter: MovieAdapter

    private var listener: MovieClickListener? = null

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
        scope.launch {
            val list = loadMovies(requireContext())
            adapter.setData(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        scope.cancel()
    }

    fun setClickListener(l: MovieClickListener?) {
        listener = l
    }

    interface MovieClickListener {

        fun onClick(movieId: Int)
    }

    override fun onClick(movie: Movie) {
        listener?.onClick(movie.id)
    }
}