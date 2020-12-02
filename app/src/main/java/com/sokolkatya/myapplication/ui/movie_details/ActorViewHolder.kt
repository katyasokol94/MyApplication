package com.sokolkatya.myapplication.ui.movie_details

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sokolkatya.myapplication.R
import com.sokolkatya.myapplication.ui.entities.Actor
import com.sokolkatya.myapplication.ui.view.SquareShapeableImageView

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var ivPhoto: SquareShapeableImageView = itemView.findViewById(R.id.iv_movie_actor)
    private var tvName: TextView = itemView.findViewById(R.id.tv_movie_actor_name)

    fun bind(actor: Actor) {
        tvName.text = itemView.context.getString(actor.name)
        ivPhoto.setImageResource(actor.photo)
    }
}