package com.carfax.application.cache

import android.content.Context
import android.content.SharedPreferences

/**
 * General Preferences Helper class, used for storing preference values using the Preference API
 */
open class PreferencesHelper(context: Context) {

    companion object {
        private const val PREF_PACKAGE_NAME = "com.carfax.preferences"

        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(PREF_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Store and retrieve the last time data was cached
     */
    var lastCacheTime: Long
        get() = pref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = pref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

}