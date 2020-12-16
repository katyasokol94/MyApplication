package com.sokolkatya.myapplication.ui.movie_details

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sokolkatya.myapplication.R
import com.sokolkatya.myapplication.extension.dipI

class FragmentMoviesDetails : Fragment() {

    private lateinit var tvBack: TextView
    private lateinit var rvActors: RecyclerView

    private var listener: MovieDetailsClickListener? = null
    private lateinit var adapter: ActorAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
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
        //TODO: Implement loading data
//        adapter.setData()
    }

    fun setClickListener(l: MovieDetailsClickListener?) {
        listener = l
    }

    interface MovieDetailsClickListener {

        fun onBackClick()
    }
}