package com.veselovvv.drinks.presentation.core

import android.view.View
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun CircleImageView.loadImage(view: View, url: String) = Glide.with(view).load(url).into(this)