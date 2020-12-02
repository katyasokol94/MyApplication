package com.sokolkatya.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sokolkatya.myapplication.R

class FragmentMoviesDetails : Fragment() {

    private var listener: MovieDetailsClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvBack: TextView = view.findViewById(R.id.tv_movie_back)
        tvBack.setOnClickListener {
            listener?.onBackClick()
        }
    }

    fun setClickListener(l: MovieDetailsClickListener?) {
        listener = l
    }

    interface MovieDetailsClickListener {
        fun onBackClick()
    }
}