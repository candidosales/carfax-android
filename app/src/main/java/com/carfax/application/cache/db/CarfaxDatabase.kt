package com.carfax.application.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.carfax.application.cache.model.CachedCar
import com.carfax.application.cache.dao.CachedCarDao

@Database(entities = arrayOf(CachedCar::class), version = 1)
abstract class CarfaxDatabase : RoomDatabase() {

    abstract fun cachedCarDao(): CachedCarDao

    private var INSTANCE: CarfaxDatabase? = null

    private val sLock = Any()

    fun getInstance(context: Context): CarfaxDatabase {
        if (INSTANCE == null) {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CarfaxDatabase::class.java,
                        "carfax.db")
                        .build()
                }
                return INSTANCE!!
            }
        }
        return INSTANCE!!
    }

}