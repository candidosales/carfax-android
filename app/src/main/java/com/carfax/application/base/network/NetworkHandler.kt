package com.carfax.application.base.network

import android.content.Context
import com.carfax.application.base.extensions.networkInfo

/**
 * Injectable class which handles device network connection.
 */
class NetworkHandler constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnectedOrConnecting ?: false
}