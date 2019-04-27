package com.carfax.application.cars.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Car (
    val photo: String,
    val dealer: Dealer?,
    val price: BigDecimal,
    val year: Int,
    val make: String,
    val model: String,
    val trim: String,
    val mileage: Int
): Parcelable