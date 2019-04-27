package com.carfax.application.cars.model.response

import com.carfax.application.cars.model.Dealer
import com.squareup.moshi.Json

data class CarResponse(
    @Json(name = "id") val id: String,
    @Json(name = "images") val images: CarImagesResponse,
    @Json(name = "year")val year: Int,
    @Json(name = "make")val make: String,
    @Json(name = "model")val model: String,
    @Json(name = "trim")val trim: String,
    @Json(name = "price")val price: Float,
    @Json(name = "mileage")val mileage: Int,
    @Json(name = "dealer") val dealer: Dealer
)