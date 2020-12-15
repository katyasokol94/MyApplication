package com.sokolkatya.myapplication.ui.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sokolkatya.myapplication.R
import com.sokolkatya.myapplication.ui.entities.Actor

class ActorAdapter : ListAdapter<Actor, ActorViewHolder>(DiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
            ActorViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.view_holder_actor, parent, false)
            )

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(actors: List<Actor>) {
        submitList(actors)
    }

    private class DiffItemCallback : DiffUtil.ItemCallback<Actor>() {

        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean =
                oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean =
                oldItem == newItem
    }
}