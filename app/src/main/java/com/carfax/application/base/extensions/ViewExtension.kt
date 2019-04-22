package com.carfax.application.base.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Extension function to hide or show a view
 *
 * @return true if the view is visible or false otherwise
 */
fun View.hideOrShow(show: Boolean) {
    if (show) visible() else gone()
}

/**
 * Extension function to change the view visibility to Visible
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Extension function to change the view visibility to Gone
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Extension function to inflate a layout
 *
 * @param layoutRes The resource layout id
 * @return The inflated view using this ViewGroup as root
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)