package com.carfax.application.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal


@Entity(tableName = "cars")
data class CachedCar(

    @PrimaryKey
    var id: String,
    val photo: String,
    val price: Double,
    val year: Int,
    val make: String,
    val model: String,
    val trim: String,
    val mileage: Int
)