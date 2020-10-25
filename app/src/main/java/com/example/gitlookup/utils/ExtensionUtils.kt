package com.example.gitlookup.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

fun View.show(show: Boolean = true) {
    if (show) {
        visibility = View.VISIBLE
    } else {
        visibility = View.GONE
    }
}

fun View.hide() {
    visibility = View.GONE
}

fun ImageView.applyImage(imageUri: String?, placeholder: Int? = null, circleCrop: Boolean = false) {
    show()
    var builder = Glide.with(this).load(imageUri)

    placeholder?.let {
        builder = builder.placeholder(it).error(it)
    }

    if(circleCrop) {
        builder = builder.circleCrop()
    }

    builder.into(this)
}

fun TextView.applyText(text: String?) {
    text?.let {
        show()
        setText(text)
    } ?: run {
        hide()
    }
}
