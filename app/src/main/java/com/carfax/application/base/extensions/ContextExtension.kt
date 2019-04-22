package com.carfax.application.base.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.annotation.StringRes


/**
 * Extension property to get a [NetworkInfo]
 *
 * @return The active network info in the Android Framework
 */
val Context.networkInfo: NetworkInfo?
    get() {
        val service = this.getSystemService(Context.CONNECTIVITY_SERVICE) ?: return null
        return (service as ConnectivityManager).activeNetworkInfo
    }

/**
 * Extension function to show toast
 *
 */
fun Context.toast(@StringRes stringResId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, stringResId, duration).show()

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()