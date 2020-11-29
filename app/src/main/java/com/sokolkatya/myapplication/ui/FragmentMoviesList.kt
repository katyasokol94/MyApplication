package com.sokolkatya.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.sokolkatya.myapplication.R

class FragmentMoviesList : Fragment() {

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

        val clMovie: ConstraintLayout = view.findViewById(R.id.cl_movie_item)
        clMovie.setOnClickListener {
            listener?.onClick()
        }
    }

    fun setClickListener(l: MovieClickListener?) {
        listener = l
    }

    interface MovieClickListener {
        fun onClick()
    }
}