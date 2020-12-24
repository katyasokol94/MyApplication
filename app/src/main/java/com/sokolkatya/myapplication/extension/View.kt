package com.sokolkatya.myapplication.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

inline fun ImageView.loadImage(
    url: String?,
    placeholder: Drawable? = null,
    error: Drawable? = null,
    centerCrop: Boolean = false,
    circleCrop: Boolean = false,
    ctx: Context? = null
) {
    loadImageInternal(url, ctx)
            .apply(RequestOptions().apply {
                if (centerCrop) centerCrop()
                if (placeholder != null) placeholder(placeholder)
                if (error != null) error(error)
                if (circleCrop) circleCrop()
            })
            .listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                }
            )
            .into(this)
}

inline fun ImageView.loadImageInternal(url: String?, ctx: Context? = null) =
        Glide
                .with(ctx ?: context)
                .load(url)