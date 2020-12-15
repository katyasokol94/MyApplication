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
import com.sokolkatya.myapplication.ui.entities.Actor

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
        adapter.setData(composeActorsList())
    }

    private fun composeActorsList(): MutableList<Actor> =
            mutableListOf<Actor>()
                    .apply {
                        add(
                            Actor(
                                R.string.movie_details_robert_downey_jr,
                                R.drawable.movie_details_cast_1
                            )
                        )
                        add(
                            Actor(
                                R.string.movie_details_chris_evans,
                                R.drawable.movie_details_cast_2
                            )
                        )
                        add(
                            Actor(
                                R.string.movie_details_mark_ruffalo,
                                R.drawable.movie_details_cast_3
                            )
                        )
                        add(
                            Actor(
                                R.string.movie_details_chris_hemsworth,
                                R.drawable.movie_details_cast_4
                            )
                        )
                    }

    fun setClickListener(l: MovieDetailsClickListener?) {
        listener = l
    }

    interface MovieDetailsClickListener {

        fun onBackClick()
    }
}