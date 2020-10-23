package com.example.gitlookup.utils

import android.view.View

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