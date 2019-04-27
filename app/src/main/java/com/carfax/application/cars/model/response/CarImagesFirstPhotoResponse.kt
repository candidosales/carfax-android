package com.carfax.application.cars.model.response

import com.squareup.moshi.Json

data class CarImagesFirstPhotoResponse(
    @Json(name = "large") val large: String,
    @Json(name = "medium") val medium: String,
    @Json(name = "small") val small: String
)