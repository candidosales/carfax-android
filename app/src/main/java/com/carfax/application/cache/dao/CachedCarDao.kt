package com.carfax.application.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.carfax.application.cache.model.CachedCar

@Dao
interface CachedCarDao {

    @Query("SELECT * from cars")
    fun getAll(): List<CachedCar>

    @Query("SELECT * FROM cars WHERE id LIKE :id LIMIT 1")
    fun findById(id: Long): CachedCar

    @Insert
    fun insert(cachedCar: CachedCar)

    @Insert
    fun insertAll(vararg cachedCars: CachedCar)

    @Delete
    fun delete(cachedCar: CachedCar)

    @Query("DELETE FROM cars")
    fun deleteAll()
}