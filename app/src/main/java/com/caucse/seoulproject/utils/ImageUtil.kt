package com.caucse.seoulproject.utils

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

object ImageUtil {
    val TAG = "ImageUtil"

    fun setImage(imageView: ImageView, url: String) {
        val urlLowerCase = url.toLowerCase()
        Picasso.get().load(urlLowerCase)
                .into(imageView, object: Callback {
                    override fun onSuccess() {}

                    override fun onError(e: Exception?) {
                        Picasso.get().load(UrlParser.parseUrl(urlLowerCase))
                                .into(imageView)
                    }
                })
    }
}